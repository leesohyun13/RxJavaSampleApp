package m.collaborate.data.remote

import m.collaborate.data.remote.enums.KakaoSearchSortType
import m.collaborate.data.remote.model.KakaoImageResponse

interface RemoteDataSource {

    suspend fun searchImages(query: String, page: Int?, sort: KakaoSearchSortType?): KakaoImageResponse
}