package com.example.verdemar

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.verdemar.databinding.ActivityMainBinding
import com.example.verdemar.databinding.ActivityTelainicialBinding

class TelaInicialActivity : AppCompatActivity(){
    private lateinit var binding : ActivityTelainicialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelainicialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLitoralBrasileiro.setOnClickListener(){
            val intent = Intent(this, RegioesActivity::class.java)
            startActivity(intent)
        }

        binding.btnProjeto.setOnClickListener(){
            val intent = Intent(this, ProjetoActivity::class.java)
            startActivity(intent)
        }

        binding.btnRanking.setOnClickListener(){
            val intent = Intent(this, RankingActivity::class.java)
            startActivity(intent)
        }

        binding.btnPontoColeta.setOnClickListener(){
            val intent = Intent(this, PontoColetaActivity::class.java)
            startActivity(intent)
        }


    }
}