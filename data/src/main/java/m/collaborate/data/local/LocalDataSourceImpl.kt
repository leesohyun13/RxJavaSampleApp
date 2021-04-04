package m.collaborate.data.local

import androidx.paging.PagingSource
import m.collaborate.data.local.Dao.ImageDao
import m.collaborate.data.local.Dao.RemoteKeyDao
import m.collaborate.data.local.entity.Image
import m.collaborate.data.local.entity.RemoteKey
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val imageDao: ImageDao,
    private val remoteKeyDao: RemoteKeyDao
) : LocalDataSource {

    override fun getPagedImages(): PagingSource<Int, Image> {
        return imageDao.selectPagedImages()
    }

    override suspend fun searchImageById(url: String): Image {
        return imageDao.selectItemById(url)
    }

    override suspend fun insertImages(image: List<Image>) {
        imageDao.insertAllImages(image)
    }

    override fun isLikeImageById(isLike: Boolean, url: String) {
        imageDao.isLikeImageById(isLike, url)
    }

    override suspend fun deleteImageById(url: String) {
        imageDao.deleteItemById(url)
    }

    override suspend fun deleteImages() {
        imageDao.deleteImages()
    }

    override suspend fun insertOrReplace(remoteKeys: List<RemoteKey>): List<Long> {
        return remoteKeyDao.insertOrReplace(remoteKeys)
    }

    override suspend fun remoteKeyById(url: String): RemoteKey? {
        return remoteKeyDao.remoteKeyById(url)
    }

    override suspend fun clearRemoteKeys() {
        remoteKeyDao.clearRemoteKeys()
    }
}