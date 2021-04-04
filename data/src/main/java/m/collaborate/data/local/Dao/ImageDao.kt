package m.collaborate.data.local.Dao

import androidx.paging.PagingSource
import androidx.room.*
import androidx.room.Dao
import m.collaborate.data.local.entity.Image

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllImages(books: List<Image>)

    @Query("SELECT * FROM image")
    fun selectPagedImages(): PagingSource<Int, Image>

    @Query("SELECT * FROM image WHERE imageUrl = :id")
    fun selectItemById(id: String): Image

    @Query("UPDATE image SET isLike = :isLike WHERE imageUrl = :id")
    fun isLikeImageById(isLike: Boolean, id: String)

    @Query("DELETE FROM image")
    suspend fun deleteImages()

    @Query("DELETE FROM image WHERE imageUrl = :id")
    fun deleteItemById(id: String): Image
}