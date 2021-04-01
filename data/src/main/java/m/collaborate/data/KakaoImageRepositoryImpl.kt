package m.collaborate.data

import androidx.lifecycle.LiveData
import m.collaborate.data.local.LocalDataSource
import m.collaborate.data.local.entity.Image
import m.collaborate.data.remote.model.KakaoImageResponse
import m.collaborate.data.remote.RemoteDataSource
import m.collaborate.data.remote.model.wrapper.ResWrapper
import javax.inject.Inject

class KakaoImageRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : KakaoImageRepository {
    override suspend fun searchImage(searchText: String): ResWrapper<KakaoImageResponse> {
        TODO("Not yet implemented")
    }

    override fun getImages(): LiveData<List<Image>> {
        TODO("Not yet implemented")
    }

    override suspend fun saveImages(images: List<Image>) {
        TODO("Not yet implemented")
    }

    override suspend fun clearImages() {
        TODO("Not yet implemented")
    }
}