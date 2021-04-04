package m.collaborate.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "image")
data class Image(
    @PrimaryKey @field:SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("collection")
    val collection: String,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,
    @SerializedName("width")
    val width: Int,
    @SerializedName("height")
    val height: Int,
    @SerializedName("display_sitename")
    val displaySiteName: String,
    @SerializedName("doc_url")
    val docUrl: String,
    @SerializedName("datetime")
    val datetime: String, // format [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]
    @SerializedName("is_like")
    val isLike: Boolean
)