package id.melur.eskalinktest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.melur.eskalinktest.database.Dummy
import id.melur.eskalinktest.database.DummyDatabase
import id.melur.eskalinktest.databinding.ItemDataBinding
import id.melur.eskalinktest.model.DataItem
import id.melur.eskalinktest.model.DataResponse
import java.security.AccessController.getContext


class DataAdapter(
    private val onDelete : (Dummy) -> Unit,
    private val onEdit : (Dummy) -> Unit,
    private val listener: DataActionListener
) : ListAdapter<Dummy, DataAdapter.DataViewHolder>(DIFF_CALLBACK)  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    inner class DataViewHolder(private val binding: ItemDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Dummy) {
            with(binding) {
                tvNik.text = data.nik
                tvNama.text = data.nama
                tvUmur.text = data.umur.toString()
                tvKota.text = data.kota

                btnDelete.setOnClickListener {
                    onDelete.invoke(data)
                    listener.onDelete(data)
                }

                btnEdit.setOnClickListener {
                    onEdit.invoke(data)
                    listener.onEdit(data)
                }
            }

//            itemView.setOnClickListener { listener(data) }
        }
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Dummy>() {
            override fun areItemsTheSame(oldItem: Dummy, newItem: Dummy): Boolean {
                return oldItem.nik == newItem.nik
            }

            override fun areContentsTheSame(oldItem: Dummy, newItem: Dummy): Boolean {
                return oldItem == newItem
            }
        }
    }
}


interface DataActionListener {
    fun onDelete(dummy: Dummy)
    fun onEdit(dummy: Dummy)
}
