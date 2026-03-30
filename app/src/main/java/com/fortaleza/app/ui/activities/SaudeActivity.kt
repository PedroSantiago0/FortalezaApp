package com.fortaleza.app.ui.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fortaleza.app.data.model.DataProvider
import com.fortaleza.app.databinding.ActivitySaudeBinding
import com.fortaleza.app.ui.adapters.HealthUnitAdapter

class SaudeActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySaudeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaudeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener { finish() }

        binding.tvAgentPhone.setOnClickListener {
            callPhone("8534521234")
        }

        val adapter = HealthUnitAdapter(
            onCall = { phone -> callPhone(phone) },
            onMap = { address -> openMaps(address) }
        )
        binding.rvHealthUnits.layoutManager = LinearLayoutManager(this)
        binding.rvHealthUnits.adapter = adapter
        adapter.submitList(DataProvider.healthUnits)
    }

    private fun callPhone(number: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
        startActivity(intent)
    }

    private fun openMaps(address: String) {
        val encoded = Uri.encode(address)
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.google.com/maps/search/?api=1&query=$encoded")
        )
        intent.setPackage("com.google.android.apps.maps")
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            // Fallback to browser
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/maps/search/?api=1&query=$encoded")
            )
            startActivity(browserIntent)
        }
    }
}
