package m.collaborate.data.local

import m.collaborate.data.local.entity.Image

class LocalDataSourceImpl : LocalDataSource {
    override suspend fun getImages(): List<Image> {
        TODO("Not yet implemented")
    }

    override suspend fun searchImageById(url: String): Image {
        TODO("Not yet implemented")
    }

    override suspend fun insertImage(task: Image) {
        TODO("Not yet implemented")
    }

    override suspend fun insertImages(image: List<Image>) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteImageById(url: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteImages() {
        TODO("Not yet implemented")
    }
}