package com.example.datastore.dsproto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.datastore.databinding.ActivityProtoBinding

class ProtoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProtoBinding
    private lateinit var vmProto:ProtoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProtoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vmProto = ViewModelProvider(this).get(ProtoViewModel::class.java)

        binding.btnSave.setOnClickListener {
            val nama = binding.etName.text.toString()
            val umur = binding.etAge.text.toString()
            vmProto.editData(nama,umur.toInt())
        }

        binding.btnClear.setOnClickListener {
            vmProto.deleteData()
        }

        vmProto.dataUser.observe(this) {
            binding.resultName.text = it.nama
            binding.resultAge.text = it.umur.toString()
        }

    }
}