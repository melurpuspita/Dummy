package id.melur.eskalinktest.ui

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
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.melur.eskalinktest.R
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
import id.melur.eskalinktest.helper.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

//    private lateinit var dataAdapter: DataAdapter
//    private lateinit var sharedPref: SharedPreferences
//    private var movieId: Int? = 0


    private val dataAdapter: DataAdapter by lazy {
        DataAdapter(::onMovieClicked)
    }

    private val viewModel: ViewModel by viewModels()

    private var mDb: DummyDatabase? = null

    private val apiService : ApiService by lazy { DataClient.instance }
//    private val viewModel: ViewModel by viewModelsFactory { ViewModel(apiService) }


    private val observerRegister: Observer<Long> = Observer { result ->
        when (result) {
//            0L -> showToast(requireContext(), "Something is wrong")
//            else -> moveToHomeActivity()
        }
    }

    private val observer: Observer<Result<List<Dummy>>> = Observer { result ->
        when (result) {
            is Result.Loading -> {
//                setLoadState(true)
            }
            is Result.Success -> {
//                setLoadState(false)
                val movies = result.data
                dataAdapter.submitList(movies)
            }
            is Result.Error -> {
//                setLoadState(false)
            }
        }
    }

    private fun onMovieClicked(dummy: Dummy) {
//        view?.findNavController()
//            ?.navigate(MovieFragmentDirections.actionMovieFragmentToDetailMovieFragment(dummy.id))
    }


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
        test()
        getDataAPIButton()
        getData()
        addButtonOnPressed()
    }

    private fun getData() {
        viewModel.getData().observe(viewLifecycleOwner, observer)
        binding.rvData.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = dataAdapter
        }

    }

    private fun test() {
        binding.btnTest.setOnClickListener {
            deleteItemDb()
        }
    }

    private fun deleteItemDb() {
        CoroutineScope(Dispatchers.IO).launch {
            mDb?.dummyDao()?.coba()
        }
    }

    private fun getDataAPIButton(){
        binding.btnApi.setOnClickListener {
            showDataDialog()
        }
    }

    private fun addButtonOnPressed() {
        binding.addButton.setOnClickListener {
            showAlertDialog(null)
//            val dataUsername = sharedPref.getString("username", "ini default value")
        }
    }

    private fun showAlertDialog(dummy: Dummy?) {
//        getData()
        val customLayout =
            LayoutInflater.from(requireContext()).inflate(R.layout.data_dialog, null, false)

        val tvTitle = customLayout.findViewById<TextView>(R.id.textView2)
        val etNIK = customLayout.findViewById<EditText>(R.id.etNIK)
        val etNama = customLayout.findViewById<EditText>(R.id.etNama)
        val etUmur = customLayout.findViewById<EditText>(R.id.etUmur)
        val etKota = customLayout.findViewById<EditText>(R.id.etKota)
        val btnSave = customLayout.findViewById<Button>(R.id.btnSave)

        val builder = AlertDialog.Builder(requireContext())

        builder.setView(customLayout)

        val dialog = builder.create()

        if (dummy != null) {
            tvTitle.text = "Ubah Data"
            etNIK.setText(dummy.nik)
            etNama.setText(dummy.nama)
            etUmur.setText(dummy.umur.toString())
            etKota.setText(dummy.kota)

        }

        btnSave.setOnClickListener {
            val nik = etNIK.text.toString()
            val nama = etNama.text.toString()
            val umur = etUmur.text.toString().toInt()
            val kota = etKota.text.toString()
//            val username = dataUsername.toString()

            if (dummy != null) {
                val newData = Dummy(dummy.nik, nama, umur, kota)
                // update data yg sudah ada
                updateToDb(newData)
                dialog.dismiss()
            } else {
                viewModel.checkNIK(nik).observe(viewLifecycleOwner) { isExist ->
                    if (isExist) {
                        Toast.makeText(requireContext(), "NIK sudah ada", Toast.LENGTH_SHORT).show()
//                        binding.etlEmail.requestFocus()
                    } else {
                        // tambah data baru
                        viewModel.register(username, email, password)
                            .observe(viewLifecycleOwner, observerRegister)
                    }
                }
                dialog.dismiss()
            }
        }
        dialog.show()
    }

    private fun showDataDialog() {
//        getData()
        val customLayout =
            LayoutInflater.from(requireContext()).inflate(R.layout.data_dialog, null, false)

        val btnDelete = customLayout.findViewById<Button>(R.id.btnYes)
        val btnCancel = customLayout.findViewById<Button>(R.id.btnCancel)

        val builder = AlertDialog.Builder(requireContext())

        builder.setView(customLayout)

        val dialog = builder.create()

        btnDelete.setOnClickListener {
            getData()
            dialog.dismiss()
        }

        btnCancel.setOnClickListener {
//            viewModel.inserData("hihihi", "nama", 12, "kota")
//                .observe(viewLifecycleOwner, observerRegister)
//            saveToDb("123123", "melur", 12, "beks")
            dialog.dismiss()
        }
        dialog.show()
    }


    private fun btnSave() {
        binding..setOnClickListener {
            val username = binding.etUsername.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val passwordConfirm = binding.etPasswordConfirm.text.toString()

            if (validateData(username, email, password, passwordConfirm)) {
                viewModel.checkEmail(email).observe(viewLifecycleOwner) { isExist ->
                    if (isExist) {
                        binding.etlEmail.error = "Email sudah ada"
                        binding.etlEmail.requestFocus()
                    } else {
                        viewModel.register(username, email, password)
                            .observe(viewLifecycleOwner, observerRegister)
                    }
                }
            }
        }
    }

    private fun validateData(
        nik: String,
    ): Boolean {
        return when {
            username.isEmpty() -> {
                binding.etlUsername.error = "Username tidak boleh kosong"
                binding.etlUsername.requestFocus()
                false
            }
            email.isEmpty() -> {
                binding.etlEmail.error = "Email tidak boleh kosong"
                binding.etlEmail.requestFocus()
                false
            }
            !email.isValidated() -> {
                binding.etlEmail.error = "Email tidak valid"
                binding.etlEmail.requestFocus()
                false
            }
            password.isEmpty() -> {
                binding.etlPassword.error = "Password tidak boleh kosong"
                binding.etlPassword.requestFocus()
                false
            }
            password.length < MIN_PASSWORD_LENGTH -> {
                binding.etlPassword.error =
                    "Password harus lebih dari $MIN_PASSWORD_LENGTH karakter"
                binding.etlPassword.requestFocus()
                false
            }
            passwordConfirm.isEmpty() -> {
                binding.etlPasswordConfirm.error = "Konfirmasi password tidak boleh kosong"
                binding.etlPasswordConfirm.requestFocus()
                false
            }
            passwordConfirm != password -> {
                binding.etlPasswordConfirm.error = "Password tidak sesuai"
                binding.etlPasswordConfirm.requestFocus()
                false
            }
            else -> true
        }
    }
}