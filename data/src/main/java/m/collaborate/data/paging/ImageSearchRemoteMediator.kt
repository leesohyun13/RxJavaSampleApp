package m.collaborate.data.paging

import androidx.paging.*
import m.collaborate.data.local.LocalDataSource
import m.collaborate.data.local.entity.Image
import m.collaborate.data.local.entity.RemoteKey
import m.collaborate.data.network.KakaoImageApi.Companion.BOOK_STARTING_PAGE_INDEX
import m.collaborate.data.remote.RemoteDataSource
import m.collaborate.data.remote.enums.KakaoSearchSortType
import m.collaborate.data.remote.model.KakaoImageResponse
import retrofit2.HttpException
import java.io.IOException
import java.io.InvalidObjectException

@OptIn(ExperimentalPagingApi::class)
class ImageSearchRemoteMediator(
    private val query: String,
    private val sort: KakaoSearchSortType?,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : RemoteMediator<Int, Image>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Image>
    ): MediatorResult {
        return try {
            // The network load method takes an optional [String] parameter. For every page
            // after the first, we pass the [String] token returned from the previous page to
            // let it continue from where it left off. For REFRESH, pass `null` to load the
            // first page.
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getClosestRemoteKey(state)
                    remoteKeys?.nextKey?.minus(1) ?: BOOK_STARTING_PAGE_INDEX
                }
                // In this example, we never need to prepend, since REFRESH will always load the
                // first page in the list. Immediately return, reporting end of pagination.
                LoadType.PREPEND -> {
                    val remoteKeys = getFirstRemoteKey(state)
                        ?: throw InvalidObjectException("Invalid state, key should not be null")
                    //end of list condition reached
                    remoteKeys.prevKey ?: return MediatorResult.Success(endOfPaginationReached = true)
                    remoteKeys.prevKey
                }
                // Query remoteKeyDao for the next RemoteKey.
                LoadType.APPEND -> {
                    val remoteKeys = getLastRemoteKey(state)
                        ?: throw InvalidObjectException("Remote key should not be null for $loadType")
                    remoteKeys.nextKey
                }
            }

            // Suspending network load via Retrofit. This doesn't need to be wrapped in a
            // withContext(Dispatcher.IO) { ... } block since Retrofit's Coroutine CallAdapter
            // dispatches on a worker thread.
            val response = remoteDataSource.searchImages(query, page, sort)
            val images = response.documents.map { image -> documentToIImage(image) }
            val endOfPaginationReached = images.count() < state.config.pageSize

            // Store loaded data, and next key in transaction, so that they're always consistent
            if (loadType == LoadType.REFRESH) {
                with(localDataSource) {
                    localDataSource.deleteImages()
                    localDataSource.clearRemoteKeys()
                }
            }

            val prevKey = if (page == BOOK_STARTING_PAGE_INDEX) null else page?.minus(1)
            val nextKey = if (endOfPaginationReached) null else page?.plus(1)
            val keys = images.map {
                RemoteKey(imageUrl = it.imageUrl, prevKey = prevKey, nextKey = nextKey)
            }
            with(localDataSource) {
                // Update RemoteKey for this query.
                localDataSource.insertOrReplace(keys)
                // Insert new users into database, which invalidates the current
                // PagingData, allowing Paging to present the updates in the DB.
                localDataSource.insertImages(images)
            }

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    /**
     * get the last remote key inserted which had the data
     */
    private suspend fun getLastRemoteKey(state: PagingState<Int, Image>): RemoteKey? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { image -> localDataSource.remoteKeyById(image.imageUrl) }
    }

    /**
     * get the first remote key inserted which had the data
     */
    private suspend fun getFirstRemoteKey(state: PagingState<Int, Image>): RemoteKey? {
        return state.pages
            .firstOrNull() { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { image -> localDataSource.remoteKeyById(image.imageUrl) }
    }

    /**
     * get the closest remote key inserted which had the data
     */
    private suspend fun getClosestRemoteKey(state: PagingState<Int, Image>): RemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.imageUrl?.let { urlId ->
                localDataSource.remoteKeyById(urlId)
            }
        }
    }

    private fun documentToIImage(document: KakaoImageResponse.Document): Image = Image(
        imageUrl = document.imageUrl,
        collection = document.collection,
        thumbnailUrl = document.thumbnailUrl,
        width = document.width,
        height = document.height,
        displaySiteName = document.displaySiteName,
        docUrl = document.docUrl,
        datetime = document.datetime,
        isLike = false
    )
}
