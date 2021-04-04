package m.collaborate.data

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import m.collaborate.data.local.entity.Image
import m.collaborate.data.local.entity.RemoteKey
import m.collaborate.data.remote.enums.KakaoSearchSortType
import m.collaborate.data.remote.model.KakaoImageResponse
import m.collaborate.data.remote.model.wrapper.ResWrapper

interface KakaoImageRepository {

    suspend fun searchImage(query: String, page: Int, sort: KakaoSearchSortType?): ResWrapper<KakaoImageResponse>
    fun searchBookStream(query: String, target: KakaoSearchSortType?): Flow<PagingData<Image>>

    suspend fun saveImages(images: List<Image>)
    suspend fun clearImages()
    suspend fun saveRemoteKeys(remoteKeys: List<RemoteKey>)
    suspend fun getRemoteKeyWithIsbn(isbn: String): RemoteKey?
    suspend fun clearRemoteKeys()
}