package m.collaborate.data.local

import m.collaborate.data.local.entity.Image

interface LocalDataSource {

    suspend fun getImages(): List<Image>

    suspend fun searchImageById(url: String): Image

    suspend fun insertImage(task: Image)

    suspend fun insertImages(image: List<Image>)

    suspend fun deleteImageById(url: String)

    suspend fun deleteImages()
}