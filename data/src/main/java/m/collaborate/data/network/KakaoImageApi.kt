package m.collaborate.data.network

import m.collaborate.data.remote.enums.KakaoSearchSortType
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoImageApi {
    @GET(SUB_PATH_BOOK)
    fun searchImages(
        @Header(SCHEMA_REQUEST_HEADER_AUTHORIZATION) restApiKey: String = REST_API_KEY,
        @Query(SCHEMA_QUERY) query: String,
        @Query(SCHEMA_QUERY_SORT) sort: KakaoSearchSortType = KakaoSearchSortType.ACCURACY,
        @Query(SCHEMA_QUERY_PAGE) page: Int = BOOK_STARTING_PAGE_INDEX,
        @Query(SCHEMA_QUERY_SIZE) size: Int = BOOK_PAGING_SIZE,
    )

    companion object {
        const val REST_API_KEY = "KakaoAK 343fa18e242a2a558f3d5998a683f061"
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