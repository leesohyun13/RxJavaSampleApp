package m.collaborate.data.local.Dao

import androidx.paging.DataSource
import androidx.room.*
import androidx.room.Dao
import m.collaborate.data.local.entity.Image

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllImages(books: List<Image>)

    @Query("SELECT * FROM image")
    fun selectPagedBooks(): DataSource.Factory<Int, Image>

    @Query("DELETE FROM image")
    suspend fun deleteImages()
}