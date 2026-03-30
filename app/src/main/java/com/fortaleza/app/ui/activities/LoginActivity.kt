package com.fortaleza.app.ui.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fortaleza.app.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupMasks()
        setupListeners()
    }

    private fun setupListeners() {
        binding.btnEnter.setOnClickListener {
            val cpf = binding.etCpf.text.toString().trim()
            val cep = binding.etCep.text.toString().trim()
            if (cpf.length < 3 || cep.length < 3) {
                Toast.makeText(this, "Preencha CPF e CEP", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        // Underline (paintFlags not supported in XML)
        binding.btnNoRegister.paintFlags =
            binding.btnNoRegister.paintFlags or android.graphics.Paint.UNDERLINE_TEXT_FLAG

        binding.btnNoRegister.setOnClickListener {
            Toast.makeText(this, "Cadastro em breve!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupMasks() {
        binding.etCpf.addTextChangedListener(object : TextWatcher {
            private var isFormatting = false
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (isFormatting) return
                isFormatting = true
                val digits = s.toString().replace(Regex("[^0-9]"), "").take(11)
                val formatted = when {
                    digits.length > 9 -> "${digits.substring(0,3)}.${digits.substring(3,6)}.${digits.substring(6,9)}-${digits.substring(9)}"
                    digits.length > 6 -> "${digits.substring(0,3)}.${digits.substring(3,6)}.${digits.substring(6)}"
                    digits.length > 3 -> "${digits.substring(0,3)}.${digits.substring(3)}"
                    else -> digits
                }
                binding.etCpf.setText(formatted)
                binding.etCpf.setSelection(formatted.length)
                isFormatting = false
            }
        })

        binding.etCep.addTextChangedListener(object : TextWatcher {
            private var isFormatting = false
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (isFormatting) return
                isFormatting = true
                val digits = s.toString().replace(Regex("[^0-9]"), "").take(8)
                val formatted = if (digits.length > 5)
                    "${digits.substring(0,5)}-${digits.substring(5)}"
                else digits
                binding.etCep.setText(formatted)
                binding.etCep.setSelection(formatted.length)
                isFormatting = false
            }
        })
    }
}
