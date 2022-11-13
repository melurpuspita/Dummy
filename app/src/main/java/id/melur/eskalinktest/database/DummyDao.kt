package id.melur.eskalinktest.database

import androidx.room.*
import id.melur.eskalinktest.model.DataItem
import java.nio.charset.CodingErrorAction.REPLACE

@Dao
interface DummyDao {
    @Query("SELECT * FROM Dummy")
    fun getAllData() : List<Dummy>
//    @Query("SELECT * FROM Note")
//    fun getAllNotes() : List<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(dummy: Dummy) : Long

//    @Insert(onConflict = IGNORE)
//    suspend fun addUser(user: User) : Long

//    @Update
//    fun updateData(dummy: Dummy) : Int

//    @Query("DROP table Dummy")
//    fun coba(dummy: Dummy) : Int

    @Query("UPDATE Dummy SET nik = :nik, nama = :nama, umur = :umur, kota = :kota WHERE nik = :nik")
    fun updateData(nik: String, nama: String, umur: Int, kota: String) : Int

    @Delete
    fun deleteData(dummy: Dummy) : Int

    @Query("DELETE FROM Dummy")
    suspend fun coba()


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun testInsert(data: List<Dummy>)
}