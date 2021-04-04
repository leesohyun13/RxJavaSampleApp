package m.collaborate.data.remote

import m.collaborate.data.network.KakaoImageApi
import m.collaborate.data.remote.enums.KakaoSearchSortType
import m.collaborate.data.remote.model.KakaoImageResponse
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor (
    private val service: KakaoImageApi
) : RemoteDataSource {
    override suspend fun searchImages(
        query: String,
        page: Int?,
        sort: KakaoSearchSortType?
    ): KakaoImageResponse {
        return service.searchImages(query = query, page = page, sort = sort)
    }
}