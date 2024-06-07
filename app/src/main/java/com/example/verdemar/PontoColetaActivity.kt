package com.example.verdemar

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.verdemar.databinding.ActivityPontocoletaBinding
import com.example.verdemar.databinding.ActivityTarefasnorteBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PontoColetaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPontocoletaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPontocoletaBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupRecyclerView()

    }


    private fun setupRecyclerView() {
        val adpter = PontoColetaAdapter()
        adpter.submitList(listaLivros())
        binding.RecyclerViewPontoColeta.adapter = adpter
        binding.RecyclerViewPontoColeta.layoutManager = LinearLayoutManager(this)
    }

    private fun listaLivros(): List<PontoColeta>{
        return  listOf(
            PontoColeta(1, "Recicla Diadema", "Rua Padre Cicero, 120", "Das 9h as 22h", "(11) 97019-2047") ,
        )
    }

}