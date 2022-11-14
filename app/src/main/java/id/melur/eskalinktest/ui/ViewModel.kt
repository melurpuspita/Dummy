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

    fun inserData(nik: String, nama: String, umur: Int, kota: String): LiveData<Long> {
        val user = Dummy(
            nik = nik,
            nama = nama,
            umur = umur,
            kota = kota
        )

        viewModelScope.launch {
            insert.value = dataRepo.insert(user)
        }

        return insert
    }

    fun getData() = dataRepo.getAllData()

    // Get data dari database room
    fun deleteAllData() {
        // viewmodel scope penggunaannya mirip coroutinescope
        viewModelScope.launch {
//            dataRepo.coba()
        }
    }

        fun saveToDb(nik: String, nama: String, umur: Int, kota: String) {
            viewModelScope.launch {
                val dummy = Dummy(nik, nama, umur,kota)
//                dataRepo.insertData(dummy)
//                val result = mDb?.dummyDao()?.insertData(dummy)
//            mDb?.dummyDao()?.getAllData()
//            if (result != 0L) {
//                CoroutineScope(Dispatchers.Main).launch {
//                    Toast.makeText(requireContext(), "Berhasil Registrasi", Toast.LENGTH_SHORT).show()
//                }
//            } else {
//                CoroutineScope(Dispatchers.Main).launch {
//                    Toast.makeText(requireContext(), "Gagal Registrasi", Toast.LENGTH_SHORT).show()
//                }
//            }
        }
    }
}