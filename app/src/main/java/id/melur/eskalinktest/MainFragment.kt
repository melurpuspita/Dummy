package id.melur.eskalinktest

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import id.melur.eskalinktest.adapter.DataAdapter
import id.melur.eskalinktest.database.Dummy
import id.melur.eskalinktest.database.DummyDatabase
import id.melur.eskalinktest.databinding.FragmentMainBinding
import id.melur.eskalinktest.model.DataItem
import id.melur.eskalinktest.model.DataResponse
import id.melur.eskalinktest.service.ApiService
import id.melur.eskalinktest.service.DataClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var dataAdapter: DataAdapter
//    private lateinit var sharedPref: SharedPreferences
//    private var movieId: Int? = 0

    private var mDb: DummyDatabase? = null

    private val apiService : ApiService by lazy { DataClient.instance }
//    private val viewModel: ViewModel by viewModelsFactory { ViewModel(apiService) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        getReview(436270)
//        getData()
        mDb = DummyDatabase.getInstance(requireContext())

        test()
        getDataAPIButton()
        initRecyclerView()
    }

    private fun test() {
        binding.btnTest.setOnClickListener {
//            saveToDb("123123", "melur", 12, "beks")
//            mDb?.dummyDao().insertData()
            deleteItemDb()
        }
    }

    private fun saveToDb(nik: String, nama: String, umur: Int, kota: String) {
        val dummy = Dummy(null, nik, nama, umur,kota)
        CoroutineScope(Dispatchers.IO).launch {
            val result = mDb?.dummyDao()?.insertData(dummy)
            mDb?.dummyDao()?.getAllData()
            if (result != 0L) {
                CoroutineScope(Dispatchers.Main).launch {
                    Toast.makeText(requireContext(), "Berhasil Registrasi", Toast.LENGTH_SHORT).show()
                }
            } else {
                CoroutineScope(Dispatchers.Main).launch {
                    Toast.makeText(requireContext(), "Gagal Registrasi", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun deleteItemDb() {
        CoroutineScope(Dispatchers.IO).launch {
            mDb?.dummyDao()?.coba()
//            if (result != 0) {
//                getDataFromDb()
//                CoroutineScope(Dispatchers.Main).launch {
//                    Toast.makeText(requireContext(), "Berhasil Dihapus", Toast.LENGTH_SHORT).show()
//                }
//            } else {
//                CoroutineScope(Dispatchers.Main).launch {
//                    Toast.makeText(requireContext(), "Gagal Dihapus", Toast.LENGTH_SHORT).show()
//                }
//            }
        }
    }
    private fun getDataAPIButton(){
        binding.btnApi.setOnClickListener {
//            deleteItemDb()
            showDataDialog()
        }
    }

    private fun initRecyclerView() {
        dataAdapter = DataAdapter { _, data: DataItem ->

            val nik = data.nik
            val nama = data.nama
            val umur = data.umur
            val kota = data.kota

            mDb = DummyDatabase.getInstance(requireContext())

            val dummy = Dummy(null, nik, nama, umur, kota)

            mDb?.dummyDao()?.insertData(dummy)
//            val bundle = Bundle()
//            bundle.putInt("nik", nik)
//            findNavController().navigate(R.id.action_mainScreen_to_infoScreen, bundle)
        }
        binding.rvData.apply {
            adapter = dataAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    fun getData(){
        apiService.getData()
            .enqueue(object : Callback<DataResponse> {
                override fun onResponse(
                    call: Call<DataResponse>,
                    response: Response<DataResponse>
                ) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
//                            val response1 = apiService.getData()
//                            val movies = response.body()!!.data
//
//                            val movieList = movies.map { movies ->
//                                movies.toDataEntity()
//                            }
//                            mDb?.dummyDao()?.testInsert(movieList)

                            dataAdapter.updateData(response.body()!!)
                        }
                    }
                }

                override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                    Toast.makeText(requireContext(), t.toString(), Toast.LENGTH_SHORT).show()
                }
            })
    }



    private fun showDataDialog() {
//        getData()
        val customLayout =
            LayoutInflater.from(requireContext()).inflate(R.layout.data_dialog, null, false)

        val btnDelete = customLayout.findViewById<Button>(R.id.btnDelete1)
        val btnCancel = customLayout.findViewById<Button>(R.id.btnCancel)

        val builder = AlertDialog.Builder(requireContext())

        builder.setView(customLayout)

        val dialog = builder.create()

        btnDelete.setOnClickListener {
            getData()
            dialog.dismiss()
        }

        btnCancel.setOnClickListener {
            saveToDb("123123", "melur", 12, "beks")
            dialog.dismiss()
        }
        dialog.show()
    }
}