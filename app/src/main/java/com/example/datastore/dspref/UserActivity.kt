package com.example.datastore.dspref

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.asLiveData
import com.example.datastore.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userManager: UserManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userManager = UserManager(this)

        binding.btnSave.setOnClickListener {
            val nama = binding.etName.text.toString()
            val age = binding.etAge.text.toString()
            GlobalScope.launch {
                userManager.saveData(nama, age.toInt())
            }
        }

        binding.btnClear.setOnClickListener {
            GlobalScope.launch {
                userManager.clearData()

            }
        }

        userManager.userNameFlow.asLiveData().observe(this,{
            binding.resultName.text = it.toString()
        })

        userManager.userAgeFlow.asLiveData().observe(this,{
            binding.resultAge.text = it.toString()
        })

    }
}