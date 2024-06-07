package com.example.verdemar

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.verdemar.databinding.ActivityCadastrojuridicoBinding
import com.example.verdemar.databinding.ActivityRegioesBinding

class RegioesActivity: AppCompatActivity()  {
    private lateinit var binding : ActivityRegioesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegioesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgNorte.setOnClickListener(){
            val intent = Intent(this, TarefasNorteActivity::class.java)
            startActivity(intent)
        }
    }
}