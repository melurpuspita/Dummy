package id.melur.eskalinktest.database

import androidx.lifecycle.LiveData
import androidx.room.*
import id.melur.eskalinktest.model.DataItem
import java.nio.charset.CodingErrorAction.REPLACE

@Dao
interface DummyDao {
//    @Query("SELECT * FROM dummy")
//    fun getAllData() : List<Dummy>

    @Query("SELECT * FROM dummy")
    fun getData(): LiveData<List<Dummy>>

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertData(dummy: Dummy) : Long

    @Query("SELECT EXISTS(SELECT * FROM dummy WHERE nik = :nik)")
    suspend fun checkNIK(nik: String): Boolean

    @Update
    suspend fun updateData(dummy: Dummy): Int

    @Delete
    suspend fun deleteData(dummy: Dummy) : Int

    @Query("DELETE FROM dummy WHERE nik = :nik")
    suspend fun deleteData1(nik: String)

    @Query("DELETE FROM dummy")
    suspend fun coba()


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun testInsert(dummy: List<Dummy>)


    @Insert
    suspend fun register(dummy: Dummy): Long

}