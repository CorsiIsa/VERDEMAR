package com.example.verdemar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.verdemar.databinding.ActivityCadastrojuridicoBinding
import com.example.verdemar.databinding.ActivityRankingBinding

class RankingActivity : AppCompatActivity()   {

    private lateinit var binding : ActivityRankingBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRankingBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}