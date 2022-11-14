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
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.melur.eskalinktest.R
import id.melur.eskalinktest.adapter.DataActionListener
import id.melur.eskalinktest.adapter.DataAdapter
import id.melur.eskalinktest.database.Dummy
import id.melur.eskalinktest.databinding.FragmentMainBinding
import id.melur.eskalinktest.helper.Result

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var dataAdapter: DataAdapter
    private val viewModel: ViewModel by viewModels()
    private val observerRegister: Observer<Long> = Observer {}

    private val observer: Observer<Result<List<Dummy>>> = Observer { result ->
        when (result) {
            is Result.Loading -> {}
            is Result.Success -> {
                val dummy = result.data
                dataAdapter.submitList(dummy)
            }
            is Result.Error -> {}
        }
    }

    private val action = object : DataActionListener {
        override fun onDelete(dummy: Dummy) {
            showAlertDialog(dummy)
        }

        override fun onEdit(dummy: Dummy) {
            showDataDialog(dummy)
        }
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
        getDataFromAPI()
        getDataButton()
        addButtonOnPressed()
    }

    private fun getDataFromAPI() {
        viewModel.getData().observe(viewLifecycleOwner, observer)
        binding.rvData.apply {
            dataAdapter = DataAdapter({}, {}, action)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = dataAdapter
        }

    }

    private fun getDataButton(){
        binding.btnApi.setOnClickListener {
            showAlertDialog(null)
        }
    }

    private fun addButtonOnPressed() {
        binding.addButton.setOnClickListener {
            showDataDialog(null)
        }
    }

    private fun showDataDialog(dummy: Dummy?) {
        val customLayout =
            LayoutInflater.from(requireContext()).inflate(R.layout.add_dialog, null, false)

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
            val nama = etNama.text.toString()
            val umur = etUmur.text.toString().toInt()
            val kota = etKota.text.toString()

            if (dummy != null) {
                val newData = Dummy(dummy.nik, nama, umur, kota)
                // update data yg sudah ada
                viewModel.updateData(newData)
                Toast.makeText(requireContext(), "Data berhasil diperbarui", Toast.LENGTH_SHORT).show()
                dialog.dismiss()

            } else {
                val nik = etNIK.text.toString()
                viewModel.checkNIK(nik).observe(viewLifecycleOwner) { isExist ->
                    if (isExist) {
                        Toast.makeText(requireContext(), "NIK sudah ada", Toast.LENGTH_SHORT).show()
                    } else {
                        // tambah data baru
                        viewModel.inserData(nik, nama, umur, kota)
                            .observe(viewLifecycleOwner, observerRegister)
                        Toast.makeText(requireContext(), "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun showAlertDialog(dummy: Dummy?) {
        val customLayout =
            LayoutInflater.from(requireContext()).inflate(R.layout.data_dialog, null, false)

        val btnYes = customLayout.findViewById<Button>(R.id.btnYes)
        val btnCancel = customLayout.findViewById<Button>(R.id.btnCancel)
        val tvTitle = customLayout.findViewById<TextView>(R.id.tvTitle)
        val tvWarn = customLayout.findViewById<TextView>(R.id.tvWarn)

        val builder = AlertDialog.Builder(requireContext())

        builder.setView(customLayout)

        val dialog = builder.create()

        if (dummy != null) {
            tvTitle.text = "Hapus Data"
            tvWarn.text = "Apakah anda yakin ingin menghapus catatan"
            btnYes.setOnClickListener {
                // hapus data
                viewModel.deleteData(dummy.nik)
                Toast.makeText(requireContext(), "Data berhasil dihapus", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
        } else {
            btnYes.setOnClickListener {
                getDataFromAPI()
                dialog.dismiss()
            }
        }

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}