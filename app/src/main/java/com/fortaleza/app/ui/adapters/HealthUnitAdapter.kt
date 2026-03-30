package com.fortaleza.app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fortaleza.app.R
import com.fortaleza.app.data.model.HealthUnit
import com.fortaleza.app.databinding.ItemHealthUnitBinding

class HealthUnitAdapter(
    private val onCall: (String) -> Unit,
    private val onMap: (String) -> Unit
) : ListAdapter<HealthUnit, HealthUnitAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHealthUnitBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemHealthUnitBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(unit: HealthUnit) {
            binding.tvNumber.text = unit.number.toString()
            binding.tvName.text = unit.name
            binding.tvAddress.text = unit.address
            binding.tvHours.text = "⏰ ${unit.hours}"

            if (unit.isOpen) {
                binding.tvOpenStatus.text = "🟢 Aberto"
                binding.tvOpenStatus.setTextColor(
                    binding.root.context.getColor(R.color.done_text)
                )
            } else {
                binding.tvOpenStatus.text = "🟡 Verificar horário"
                binding.tvOpenStatus.setTextColor(
                    binding.root.context.getColor(R.color.pending_text)
                )
            }

            binding.btnCall.setOnClickListener { onCall(unit.phone) }
            binding.btnMap.setOnClickListener { onMap(unit.address) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<HealthUnit>() {
        override fun areItemsTheSame(old: HealthUnit, new: HealthUnit) = old.number == new.number
        override fun areContentsTheSame(old: HealthUnit, new: HealthUnit) = old == new
    }
}
