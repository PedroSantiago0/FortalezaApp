package com.fortaleza.app.ui.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.fortaleza.app.data.model.DataProvider
import com.fortaleza.app.databinding.ActivityContatosBinding
import com.fortaleza.app.ui.adapters.ContactAdapter

class ContatosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContatosBinding
    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContatosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener { finish() }

        adapter = ContactAdapter { phone ->
            val number = phone.replace(Regex("[^0-9+]"), "")
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number")))
        }

        binding.rvContacts.layoutManager = LinearLayoutManager(this)
        binding.rvContacts.adapter = adapter
        adapter.submitList(DataProvider.contacts)

        binding.etSearch.addTextChangedListener { text ->
            val query = text.toString().lowercase()
            val filtered = DataProvider.contacts.filter {
                it.name.lowercase().contains(query) || it.category.lowercase().contains(query)
            }
            adapter.submitList(filtered)
        }
    }
}
