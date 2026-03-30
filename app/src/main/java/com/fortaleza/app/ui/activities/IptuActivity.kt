package com.fortaleza.app.ui.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fortaleza.app.R
import com.fortaleza.app.databinding.ActivityIptuBinding
import com.fortaleza.app.databinding.ItemInfoRowBinding

class IptuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIptuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIptuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener { finish() }

        // Use the ItemInfoRowBinding directly (ViewBinding for <include> tags)
        populateRow(binding.rowCpf,    "CPF",           "•••.•••.•••-68")
        populateRow(binding.rowCep,    "CEP",           "60000-000")
        populateRow(binding.rowIptu,   "IPTU 2025",     "Quitado ✓",   greenText = true)
        populateRow(binding.rowIssqn,  "ISSQN",         "Sem débito",  greenText = true)
        populateRow(binding.rowColeta, "Taxa de Coleta","Quitado ✓",   greenText = true)

        binding.btnIptuOnline.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.sefin.fortaleza.ce.gov.br/")))
        }

        binding.btnEmitirBoleto.setOnClickListener {
            Toast.makeText(this, "Redirecionando para emissão de boleto…", Toast.LENGTH_SHORT).show()
            startActivity(Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.sefin.fortaleza.ce.gov.br/")))
        }
    }

    private fun populateRow(
        row: ItemInfoRowBinding,
        label: String,
        value: String,
        greenText: Boolean = false
    ) {
        row.tvLabel.text = label
        row.tvValue.text = value
        if (greenText) row.tvValue.setTextColor(getColor(R.color.green_text))
    }
}
