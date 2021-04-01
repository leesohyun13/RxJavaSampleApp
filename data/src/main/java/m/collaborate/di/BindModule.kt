package m.collaborate.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import m.collaborate.data.KakaoImageRepository
import m.collaborate.data.KakaoImageRepositoryImpl
import m.collaborate.data.local.LocalDataSourceImpl
import m.collaborate.data.remote.RemoteDataSource
import m.collaborate.data.remote.RemoteDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class BindModule {

    @Binds
    abstract fun bindKakaoImageRepository(
        kakaoRepositoryImpl: KakaoImageRepositoryImpl
    ): KakaoImageRepository

    @Binds
    abstract fun bindRemoteDataSource(
        remoteDataSourceImpl: RemoteDataSourceImpl
    ): RemoteDataSource

    @Binds
    abstract fun bindLocalDataSource(
        localDataSourceImpl: LocalDataSourceImpl
    ): LocalDataSourceImpl
}