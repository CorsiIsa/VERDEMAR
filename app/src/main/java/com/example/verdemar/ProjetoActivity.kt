package com.example.verdemar

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.verdemar.databinding.ActivityProjetoBinding
import com.example.verdemar.databinding.ActivityTelainicialBinding

class ProjetoActivity : AppCompatActivity(){
    private lateinit var binding: ActivityProjetoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProjetoBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}