package m.collaborate.data.local.Dao

import androidx.room.*
import m.collaborate.data.local.entity.Image
import javax.sql.DataSource

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllImages(books: List<Image>)

    @Query("SELECT * FROM image")
    fun selectPagedBooks(): List<Image> // FIXME add paging

    @Query("DELETE FROM image")
    suspend fun deleteImages()
}