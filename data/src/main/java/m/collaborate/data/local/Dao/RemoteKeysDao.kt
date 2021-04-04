package m.collaborate.data.local.Dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import m.collaborate.data.local.entity.RemoteKey

@Dao
interface RemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(remoteKey: List<RemoteKey>): List<Long>

    @Query("SELECT * FROM remote_key WHERE imageUrl = :url")
    suspend fun remoteKeyById(url: String): RemoteKey?

    @Query("DELETE FROM remote_key")
    suspend fun clearRemoteKeys()
}