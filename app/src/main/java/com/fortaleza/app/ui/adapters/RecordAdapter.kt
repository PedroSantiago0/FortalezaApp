package com.fortaleza.app.ui.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fortaleza.app.R
import com.fortaleza.app.data.model.Record
import com.fortaleza.app.databinding.ItemRecordBinding
import java.io.File

class RecordAdapter(
    private val onDelete: (Record) -> Unit
) : ListAdapter<Record, RecordAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemRecordBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(record: Record) {
            binding.tvCategory.text = record.category
            binding.tvDescription.text = record.description
            binding.tvDate.text = "📅 ${record.date}"

            if (record.status == "pending") {
                binding.tvStatus.text = "⏳ Em análise"
                binding.tvStatus.setBackgroundResource(R.drawable.bg_status_pending)
                binding.tvStatus.setTextColor(binding.root.context.getColor(R.color.pending_text))
            } else {
                binding.tvStatus.text = "✅ Resolvido"
                binding.tvStatus.setBackgroundResource(R.drawable.bg_status_done)
                binding.tvStatus.setTextColor(binding.root.context.getColor(R.color.done_text))
            }

            // Photo
            val path = record.photoPath
            if (!path.isNullOrEmpty()) {
                binding.ivPhoto.visibility = View.VISIBLE
                if (path.startsWith("content://")) {
                    binding.ivPhoto.setImageURI(Uri.parse(path))
                } else {
                    val file = File(path)
                    if (file.exists()) binding.ivPhoto.setImageURI(Uri.fromFile(file))
                }
            } else {
                binding.ivPhoto.visibility = View.GONE
            }

            binding.btnDelete.setOnClickListener { onDelete(record) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Record>() {
        override fun areItemsTheSame(oldItem: Record, newItem: Record) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Record, newItem: Record) = oldItem == newItem
    }
}
