package com.fortaleza.app.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fortaleza.app.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupCards()
        showWelcomeDialog()
    }

    private fun setupCards() {
        binding.cardReclame.setOnClickListener {
            startActivity(Intent(this, ReclameActivity::class.java))
        }
        binding.cardIptu.setOnClickListener {
            startActivity(Intent(this, IptuActivity::class.java))
        }
        binding.cardSaude.setOnClickListener {
            startActivity(Intent(this, SaudeActivity::class.java))
        }
        binding.cardContatos.setOnClickListener {
            startActivity(Intent(this, ContatosActivity::class.java))
        }
        binding.btnReclame.setOnClickListener {
            startActivity(Intent(this, ReclameActivity::class.java))
        }
        binding.btnIptu.setOnClickListener {
            startActivity(Intent(this, IptuActivity::class.java))
        }
        binding.btnSaude.setOnClickListener {
            startActivity(Intent(this, SaudeActivity::class.java))
        }
        binding.btnContatos.setOnClickListener {
            startActivity(Intent(this, ContatosActivity::class.java))
        }
    }

    private fun showWelcomeDialog() {
        val dialog = WelcomeDialogFragment()
        dialog.show(supportFragmentManager, "welcome")
    }
}
