package id.melur.eskalinktest.helper

import android.content.Context
import id.melur.eskalinktest.database.Dummy
import id.melur.eskalinktest.database.DummyDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataRepo(context: Context) {

    private val mDb = DummyDatabase.getInstance(context)

    suspend fun getAllData() = withContext(Dispatchers.IO) {
        mDb?.dummyDao()?.getAllData()
    }

    suspend fun updateData(nik: String, nama: String, umur: Int, kota: String) = withContext(
        Dispatchers.IO) {
        mDb?.dummyDao()?.updateData(nik, nama, umur, kota)
    }


    suspend fun insertData(dummy: Dummy) = withContext(Dispatchers.IO) {
        mDb?.dummyDao()?.insertData(dummy)
    }

    suspend fun deleteData(dummy: Dummy) = withContext(Dispatchers.IO) {
        mDb?.dummyDao()?.deleteData(dummy)
    }

    suspend fun coba() = withContext(Dispatchers.IO) {
        mDb?.dummyDao()?.coba()
    }

//
//    suspend fun insertUser(user: User) = withContext(Dispatchers.IO) {
//        mDb?.dummyDao()?.insertUser(user)
//    }
//
//    suspend fun deleteUser(user: User) = withContext(Dispatchers.IO) {
//        mDb?.dummyDao()?.deleteUser(user)
//    }
//
//    suspend fun getRegisteredUser(username: String, password: String) = withContext(Dispatchers.IO) {
//        mDb?.dummyDao()?.getRegisteredUser(username, password)
//    }
//
//    suspend fun getUser(username: String) = withContext(Dispatchers.IO) {
//        mDb?.dummyDao()?.getUser(username)
//    }
//
//    suspend fun coba(username: String) = withContext(Dispatchers.IO) {
//        mDb?.dummyDao()?.getUser(username)
//    }
}