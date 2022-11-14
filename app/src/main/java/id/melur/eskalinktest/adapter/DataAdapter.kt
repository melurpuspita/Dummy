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
    private val onClick: (Dummy) -> Unit
) : ListAdapter<Dummy, DataAdapter.DataViewHolder>(DIFF_CALLBACK)  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    inner class DataViewHolder(private val binding: ItemDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Dummy) {
            with(binding) {
                tvAuthor.text = movie.nik
                tvDate.text = movie.nama
                tvContent.text = movie.kota
            }

            itemView.setOnClickListener { onClick(movie) }
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
