package id.melur.eskalinktest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.melur.eskalinktest.database.Dummy
import id.melur.eskalinktest.database.DummyDatabase
import id.melur.eskalinktest.databinding.ItemDataBinding
import id.melur.eskalinktest.model.DataItem
import id.melur.eskalinktest.model.DataResponse
import java.security.AccessController.getContext


class DataAdapter(private val onClickListener : (nik: Int, data: DataItem) -> Unit) : RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.nik == newItem.nik
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val listDiffer = AsyncListDiffer(this, diffCallback)

    fun updateData(data: DataResponse?) = listDiffer.submitList(data?.data)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(listDiffer.currentList[position])
    }

    override fun getItemCount(): Int = listDiffer.currentList.size

    inner class DataViewHolder(private val binding: ItemDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DataItem) {
//            val nik = item.nik
//            val nama = item.nama
//            val umur = item.umur
//            val kota = item.kota
//
//            var mDb: DummyDatabase? = null
//            val dummy = Dummy(null, nik, nama, umur, kota)
////            mDb = DummyDatabase.getInstance(getContext())
//
//            mDb?.dummyDao()?.insertData(dummy)

            binding.apply {
//                val test = item.authorDetails
                tvAuthor.text = item.nik
//                tvRate.text = test.rating.toString()
                tvDate.text = item.nama
                tvContent.text = item.kota
            }
        }
    }
}
