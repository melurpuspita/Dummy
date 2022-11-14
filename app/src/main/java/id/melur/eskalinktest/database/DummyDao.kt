package id.melur.eskalinktest.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DummyDao {
    @Query("SELECT * FROM dummy")
    fun getData(): LiveData<List<Dummy>>

    @Query("SELECT EXISTS(SELECT * FROM dummy WHERE nik = :nik)")
    suspend fun checkNIK(nik: String): Boolean

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAPIData(dummy: List<Dummy>)

    @Insert
    suspend fun insertNewData(dummy: Dummy): Long

    @Update
    suspend fun updateData(dummy: Dummy): Int

    @Query("DELETE FROM dummy WHERE nik = :nik")
    suspend fun deleteData(nik: String)

    @Query("DELETE FROM dummy")
    suspend fun deleteAllData()
}