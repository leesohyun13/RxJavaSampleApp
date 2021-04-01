package m.collaborate.data

import androidx.lifecycle.LiveData
import m.collaborate.data.local.entity.Image
import m.collaborate.data.remote.model.KakaoImageResponse
import m.collaborate.data.remote.model.wrapper.ResWrapper

interface KakaoImageRepository {

    suspend fun searchImage(searchText: String): ResWrapper<KakaoImageResponse>

    fun getImages(): LiveData<List<Image>>

    suspend fun saveImages(images: List<Image>)

    suspend fun clearImages()
}