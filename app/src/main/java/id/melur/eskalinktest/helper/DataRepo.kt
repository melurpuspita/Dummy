package id.melur.eskalinktest.helper

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import id.melur.eskalinktest.database.Dummy
import id.melur.eskalinktest.database.DummyDao
import id.melur.eskalinktest.service.ApiService
import javax.inject.Inject

class DataRepo @Inject constructor(
    private val apiService: ApiService,
    private val dummyDao: DummyDao
) {

    fun getAllData(): LiveData<Result<List<Dummy>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getData()
            val dummy = response.data
            val dummyList = dummy.map { dummy ->
                dummy.toDataEntity()
            }
            dummyDao.deleteAllData()
            dummyDao.insertAPIData(dummyList)
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
        val localData: LiveData<Result<List<Dummy>>> =
            dummyDao.getData().map { Result.Success(it) }
        emitSource(localData)
    }

    suspend fun insertNewData(data: Dummy): Long =
        dummyDao.insertNewData(data)

    suspend fun checkNIK(nik: String): Boolean =
        dummyDao.checkNIK(nik)

    suspend fun updateData(dummy: Dummy): Int =
        dummyDao.updateData(dummy)

    suspend fun deleteData(nik: String) =
        dummyDao.deleteData(nik)

}