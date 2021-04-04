package m.collaborate.data.local

import androidx.paging.PagingSource
import m.collaborate.data.local.entity.Image
import m.collaborate.data.local.entity.RemoteKey

interface LocalDataSource {

    fun getPagedImages(): PagingSource<Int, Image>

    suspend fun searchImageById(url: String): Image

    suspend fun insertImages(image: List<Image>)

    fun isLikeImageById(isLike: Boolean, url: String)

    suspend fun deleteImageById(url: String)

    suspend fun deleteImages()

    suspend fun insertOrReplace(remoteKeys: List<RemoteKey>): List<Long>
    suspend fun remoteKeyById(url: String): RemoteKey?
    suspend fun clearRemoteKeys()
}