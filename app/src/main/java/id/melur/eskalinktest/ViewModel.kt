package id.melur.eskalinktest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.melur.eskalinktest.database.Dummy
import id.melur.eskalinktest.helper.DataRepo
import id.melur.eskalinktest.service.ApiService
import kotlinx.coroutines.launch

class ViewModel(private val dataRepo: DataRepo, private val apiService: ApiService) : ViewModel() {



    // Get data dari database room
    fun deleteAllData() {
        // viewmodel scope penggunaannya mirip coroutinescope
        viewModelScope.launch {
            dataRepo.coba()
        }
    }

        fun saveToDb(nik: String, nama: String, umur: Int, kota: String) {
            viewModelScope.launch {
                val dummy = Dummy(null, nik, nama, umur,kota)
                dataRepo.insertData(dummy)
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