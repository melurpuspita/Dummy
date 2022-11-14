package id.melur.eskalinktest.database

import androidx.lifecycle.LiveData
import androidx.room.*
import id.melur.eskalinktest.model.DataItem
import java.nio.charset.CodingErrorAction.REPLACE

@Dao
interface DummyDao {
    @Query("SELECT * FROM dummy")
    fun getAllData() : List<Dummy>
//    @Query("SELECT * FROM Note")
//    fun getAllNotes() : List<Note>

    @Query("SELECT * FROM dummy")
    fun getData(): LiveData<List<Dummy>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(dummy: Dummy) : Long

//    @Insert(onConflict = IGNORE)
//    suspend fun addUser(user: User) : Long

//    @Update
//    fun updateData(dummy: Dummy) : Int

//    @Query("DROP table Dummy")
//    fun coba(dummy: Dummy) : Int

    @Query("SELECT EXISTS(SELECT * FROM dummy WHERE nik = :nik)")
    suspend fun checkNIK(nik: String): Boolean


    @Query("UPDATE dummy SET nik = :nik, nama = :nama, umur = :umur, kota = :kota WHERE nik = :nik")
    fun updateData(nik: String, nama: String, umur: Int, kota: String) : Int

    @Delete
    fun deleteData(dummy: Dummy) : Int

    @Query("DELETE FROM dummy")
    suspend fun coba()


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun testInsert(dummy: List<Dummy>)


    @Insert
    suspend fun register(dummy: Dummy): Long
}