package com.example.verdemar

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.verdemar.databinding.ActivityMainBinding
import com.example.verdemar.databinding.ActivityTipocadastroBinding

class TipoCadastroActivity: AppCompatActivity()  {
    private lateinit var binding : ActivityTipocadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTipocadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgCadastroFisico.setOnClickListener(){
            val intent = Intent(this, CadastroFisicoActivity::class.java)
            startActivity(intent)
        }
        binding.imgCadastroJuridico.setOnClickListener(){
            val intent = Intent(this, CadastroJuridicoActivity::class.java)
            startActivity(intent)
        }
    }
}