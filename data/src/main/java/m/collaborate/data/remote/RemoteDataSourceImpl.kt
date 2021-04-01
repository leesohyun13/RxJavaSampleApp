package m.collaborate.data.remote

import m.collaborate.data.remote.enums.KakaoSearchSortType
import m.collaborate.data.remote.model.KakaoImageResponse

class RemoteDataSourceImpl : RemoteDataSource {
    override suspend fun searchImages(
        query: String,
        page: Int,
        target: KakaoSearchSortType?
    ): KakaoImageResponse {
        TODO("Not yet implemented")
    }
}