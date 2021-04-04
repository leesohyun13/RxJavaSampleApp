package m.collaborate.data.network

import m.collaborate.data.BuildConfig
import m.collaborate.data.remote.enums.KakaoSearchSortType
import m.collaborate.data.remote.model.KakaoImageResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoImageApi {
    @GET(SUB_PATH_BOOK)
    fun searchImages(
        @Header(SCHEMA_REQUEST_HEADER_AUTHORIZATION) restApiKey: String = BuildConfig.KAKAO_IMAGE_REST_API_KEY,
        @Query(SCHEMA_QUERY) query: String,
        @Query(SCHEMA_QUERY_SORT) sort: KakaoSearchSortType?,
        @Query(SCHEMA_QUERY_PAGE) page: Int? = BOOK_STARTING_PAGE_INDEX,
        @Query(SCHEMA_QUERY_SIZE) size: Int = BOOK_PAGING_SIZE,
    ): KakaoImageResponse

    companion object {
        const val SUB_PATH_BOOK = "v3/search/image"
        const val BOOK_STARTING_PAGE_INDEX = 1
        const val BOOK_PAGING_SIZE = 50

        const val SCHEMA_REQUEST_HEADER_AUTHORIZATION = "Authorization"
        const val SCHEMA_QUERY = "query"
        const val SCHEMA_QUERY_SORT = "sort"
        const val SCHEMA_QUERY_PAGE = "page"
        const val SCHEMA_QUERY_SIZE = "size"
    }
}