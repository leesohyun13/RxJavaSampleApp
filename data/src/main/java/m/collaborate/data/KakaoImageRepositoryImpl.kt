package m.collaborate.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import m.collaborate.data.local.LocalDataSource
import m.collaborate.data.local.entity.Image
import m.collaborate.data.local.entity.RemoteKey
import m.collaborate.data.network.KakaoImageApi
import m.collaborate.data.paging.ImageSearchRemoteMediator
import m.collaborate.data.remote.RemoteDataSource
import m.collaborate.data.remote.enums.KakaoSearchSortType
import m.collaborate.data.remote.network.safeAPi
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class KakaoImageRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : KakaoImageRepository {
    override suspend fun searchImage(query: String, page: Int, sort: KakaoSearchSortType?) = safeAPi {
        remoteDataSource.searchImages(query, page, sort)
    }

    override fun searchBookStream(
        query: String,
        target: KakaoSearchSortType?,
    ): Flow<PagingData<Image>> {
        val pagingSourceFactory = { localDataSource.getPagedImages() }
        return Pager(
            config = PagingConfig(pageSize = KakaoImageApi.BOOK_STARTING_PAGE_INDEX, enablePlaceholders = false),
            remoteMediator = ImageSearchRemoteMediator(query, target, remoteDataSource, localDataSource),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override suspend fun saveImages(images: List<Image>) {
        localDataSource.insertImages(images)
    }

    override suspend fun clearImages() {
        localDataSource.deleteImages()
    }

    override suspend fun saveRemoteKeys(remoteKeys: List<RemoteKey>) {
        localDataSource.insertOrReplace(remoteKeys)
    }

    override suspend fun getRemoteKeyWithIsbn(imageUrl: String): RemoteKey? {
        return localDataSource.remoteKeyById(imageUrl)
    }

    override suspend fun clearRemoteKeys() {
        localDataSource.clearRemoteKeys()
    }
}