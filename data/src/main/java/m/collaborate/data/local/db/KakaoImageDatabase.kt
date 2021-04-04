package m.collaborate.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import m.collaborate.data.local.Dao.ImageDao
import m.collaborate.data.local.Dao.RemoteKeyDao
import m.collaborate.data.local.entity.Image
import m.collaborate.data.local.entity.RemoteKey

@Database(
        entities = [Image::class, RemoteKey::class] ,
        version = 1,
        exportSchema = false
)
abstract class KakaoImageDatabase : RoomDatabase(){
    abstract fun imageDao(): ImageDao
    abstract fun remoteKeyDao(): RemoteKeyDao

    companion object {
        private const val IMAGE_DB = "kakao-image.db"

        @Volatile
        private var INSTANCE : KakaoImageDatabase? = null

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(this){
            INSTANCE ?: getInstance(context).also {
                INSTANCE = it
            }
        }

        private fun getInstance(context: Context): KakaoImageDatabase {
            return Room.databaseBuilder(
                    context.applicationContext,
                    KakaoImageDatabase::class.java,
                    IMAGE_DB
            ).build()
        }
    }
}