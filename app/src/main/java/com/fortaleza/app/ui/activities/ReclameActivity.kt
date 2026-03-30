package com.fortaleza.app.ui.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fortaleza.app.databinding.ActivityReclameBinding
import com.fortaleza.app.data.model.Record
import com.fortaleza.app.ui.adapters.RecordAdapter
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class ReclameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReclameBinding
    private val viewModel: RecordViewModel by viewModels()
    private var currentPhotoPath: String? = null
    private var currentTab = 0 // 0=new, 1=my records
    private lateinit var recordAdapter: RecordAdapter

    // Camera result
    private val takePicture = registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success && currentPhotoPath != null) {
            showPhotoPreview(Uri.fromFile(File(currentPhotoPath!!)))
        }
    }

    // Gallery result
    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            currentPhotoPath = uri.toString()
            showPhotoPreview(uri)
        }
    }

    // Camera permission
    private val requestCamera = registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        if (granted) launchCamera() else
            Toast.makeText(this, "Permissão de câmera necessária", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReclameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupTabs()
        setupRecyclerView()
        setupButtons()
        observeRecords()
    }

    private fun setupTabs() {
        binding.btnTabNew.setOnClickListener { switchTab(0) }
        binding.btnTabMy.setOnClickListener { switchTab(1) }
        switchTab(0)
    }

    private fun switchTab(tab: Int) {
        currentTab = tab
        val isNew = tab == 0
        binding.btnTabNew.isSelected = isNew
        binding.btnTabMy.isSelected = !isNew
        binding.layoutNewRecord.visibility = if (isNew) android.view.View.VISIBLE else android.view.View.GONE
        binding.layoutMyRecords.visibility = if (!isNew) android.view.View.VISIBLE else android.view.View.GONE
    }

    private fun setupRecyclerView() {
        recordAdapter = RecordAdapter { record ->
            viewModel.delete(record)
        }
        binding.rvRecords.apply {
            layoutManager = LinearLayoutManager(this@ReclameActivity)
            adapter = recordAdapter
        }
    }

    private fun observeRecords() {
        viewModel.allRecords.observe(this) { records ->
            recordAdapter.submitList(records)
            binding.tvEmptyRecords.visibility =
                if (records.isEmpty()) android.view.View.VISIBLE else android.view.View.GONE
        }
    }

    private fun setupButtons() {
        binding.btnBack.setOnClickListener { finish() }

        binding.btnCamera.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
                launchCamera()
            } else {
                requestCamera.launch(Manifest.permission.CAMERA)
            }
        }

        binding.btnGallery.setOnClickListener {
            pickImage.launch("image/*")
        }

        binding.btnSend.setOnClickListener {
            submitRecord()
        }

        binding.ivPhotoPreview.setOnClickListener {
            // Clear photo
            currentPhotoPath = null
            binding.cardPhotoPreview.visibility = android.view.View.GONE
            binding.layoutPhotoButtons.visibility = android.view.View.VISIBLE
        }
    }

    private fun launchCamera() {
        val photoFile = createImageFile()
        val photoUri = FileProvider.getUriForFile(
            this, "${packageName}.fileprovider", photoFile
        )
        currentPhotoPath = photoFile.absolutePath
        takePicture.launch(photoUri)
    }

    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir)
    }

    private fun showPhotoPreview(uri: Uri) {
        binding.ivPhotoPreview.setImageURI(uri)
        binding.cardPhotoPreview.visibility = android.view.View.VISIBLE
        binding.layoutPhotoButtons.visibility = android.view.View.GONE
    }

    private fun submitRecord() {
        val desc = binding.etDescription.text.toString().trim()
        val category = binding.spinnerCategory.selectedItem.toString()
        if (desc.isEmpty()) {
            Toast.makeText(this, "Descreva o problema", Toast.LENGTH_SHORT).show()
            return
        }
        val date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        val record = Record(
            category = category,
            description = desc,
            photoPath = currentPhotoPath,
            date = date,
            status = "pending"
        )
        viewModel.insert(record)
        binding.etDescription.setText("")
        currentPhotoPath = null
        binding.cardPhotoPreview.visibility = android.view.View.GONE
        binding.layoutPhotoButtons.visibility = android.view.View.VISIBLE
        Toast.makeText(this, "✅ Registro enviado com sucesso!", Toast.LENGTH_SHORT).show()
        switchTab(1)
    }
}
