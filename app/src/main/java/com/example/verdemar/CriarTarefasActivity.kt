package com.example.verdemar

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.verdemar.databinding.ActivityCriartarefasBinding
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore





class CriarTarefasActivity: AppCompatActivity() {

    private lateinit var binding: ActivityCriartarefasBinding

    private val db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCriartarefasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        inserindoDadosTarefas()

    }

    private fun inserindoDadosTarefas() {
        var nomePraia = ""
        var desc = ""
        var dtTarefa = ""
        var urlFoto = ""
        var status = "Ativa"
        binding.NomeTarefa.doOnTextChanged { text, start, before, count ->
            nomePraia = text.toString()
        }
        binding.DescTarefa.doOnTextChanged { text, start, before, count ->
            desc = text.toString()
        }
        binding.DataTarefa.doOnTextChanged { text, start, before, count ->
            dtTarefa = text.toString()
        }

        binding.btnCadastrarTarefa.setOnClickListener {
            val tarefa = Tarefas(
                nomePraia = nomePraia,
                descricao = desc,
                data = dtTarefa,
                urlFoto = urlFoto,
                status = status
            )
            adicionarTarefaAoFirestore(tarefa)
        }
    }

    private fun adicionarTarefaAoFirestore(tarefa: Tarefas) {
        db.collection("tarefas")
            .add(tarefa)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(this, "Tarefas criadas corretamente..", Toast.LENGTH_LONG).show()
                val intent = Intent(this, TarefasNorteActivity::class.java)
                startActivity(intent)
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Erro ao criar tarefa.", Toast.LENGTH_LONG).show()
            }
    }
}
