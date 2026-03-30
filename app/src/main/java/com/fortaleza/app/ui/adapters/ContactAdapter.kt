package com.fortaleza.app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fortaleza.app.data.model.Contact
import com.fortaleza.app.databinding.ItemContactBinding

class ContactAdapter(
    private val onCall: (String) -> Unit
) : ListAdapter<Contact, ContactAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemContactBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(contact: Contact) {
            binding.tvIcon.text = contact.icon
            binding.tvName.text = contact.name
            binding.tvCategory.text = contact.category
            binding.tvPhone.text = contact.phone
            binding.btnCall.setOnClickListener { onCall(contact.phone) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(old: Contact, new: Contact) = old.phone == new.phone
        override fun areContentsTheSame(old: Contact, new: Contact) = old == new
    }
}
