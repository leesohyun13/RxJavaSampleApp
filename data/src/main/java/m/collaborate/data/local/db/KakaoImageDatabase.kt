package m.collaborate.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import m.collaborate.data.local.Dao.ImageDao
import m.collaborate.data.local.entity.Image

@Database(
        entities = [Image::class] ,
        version = 1,
        exportSchema = false
)
abstract class KakaoImageDatabase : RoomDatabase(){
    abstract fun imageDao(): ImageDao

    companion object {
        private var INSTANCE : KakaoImageDatabase? = null

        fun getInstance(context: Context): KakaoImageDatabase {
            return Room.databaseBuilder(
                    context.applicationContext,
                    KakaoImageDatabase::class.java,
                    "kakao-image.db"
            ).build()
        }
    }
}