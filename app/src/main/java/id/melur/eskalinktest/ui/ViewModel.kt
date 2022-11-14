package id.melur.eskalinktest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.melur.eskalinktest.database.Dummy
import id.melur.eskalinktest.helper.DataRepo
import id.melur.eskalinktest.service.ApiService
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
    private val dataRepo: DataRepo,
) : ViewModel() {

    private val insert = MutableLiveData<Long>()
    private var isExist = MutableLiveData<Boolean>()

    fun inserData(nik: String, nama: String, umur: Int, kota: String): LiveData<Long> {
        val data = Dummy(
            nik = nik,
            nama = nama,
            umur = umur,
            kota = kota
        )

        viewModelScope.launch {
            insert.value = dataRepo.insert(data)
        }

        return insert
    }

    fun getData() = dataRepo.getAllData()

    fun checkNIK(nik: String): LiveData<Boolean> {
        viewModelScope.launch {
            isExist.value = dataRepo.checkNIK(nik)
        }

        return isExist
    }
}