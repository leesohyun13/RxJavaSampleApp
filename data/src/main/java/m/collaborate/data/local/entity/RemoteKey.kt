package m.collaborate.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "remote_key")
data class RemoteKey(
    @PrimaryKey
    @field:SerializedName("image_url")
    val imageUrl: String,
    @field:SerializedName("prev_key")
    val prevKey: Int?,
    @SerializedName("next_key")
    val nextKey: Int?
)
