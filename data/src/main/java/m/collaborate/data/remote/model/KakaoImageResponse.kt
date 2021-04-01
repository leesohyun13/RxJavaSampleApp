package m.collaborate.data.remote.model

import com.google.gson.annotations.SerializedName

data class KakaoImageResponse(
    @SerializedName("documents")
    val documents: List<Document>,
    @SerializedName("meta")
    val meta: Meta
) {
    data class Meta (
        @SerializedName("total_count")
        val totalCount: Int,
        @SerializedName("pageable_count")
        val pageableCount: Int,
        @SerializedName("is_end")
        val isEnd: Boolean
    )
    data class Document(
        @SerializedName("collection")
        val collection: String,
        @SerializedName("thumbnail_url")
        val thumbnailUrl: String,
        @SerializedName("image_url")
        val imageUrl: String,
        @SerializedName("width")
        val width: Int,
        @SerializedName("height")
        val height: Int,
        @SerializedName("display_sitename")
        val displaySiteName: String,
        @SerializedName("doc_url")
        val docUrl: String,
        @SerializedName("datetime")
        val datetime: String // format [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]
    )
}