package com.example.verdemar

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.verdemar.databinding.ActivityTarefasnorteBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

class TarefasNorteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTarefasnorteBinding
    private val db = FirebaseFirestore.getInstance()
    private val TarefasLista: MutableList<Tarefas> = mutableListOf()
    private val adapter = TarefasAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTarefasnorteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pegandoTarefasDoFirebase()
        setupRecyclerView()

        binding.imgAdd.setOnClickListener(){
            val intent = Intent(this, CriarTarefasActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        adapter.submitList(TarefasLista)

        adapter.onClickDeletar={
            apagandoTarefa(it.id.toString())
        }

        adapter.onClick={
            val mFragment = EditarTarefaFragment()
            val bundle = Bundle().apply {
                putString("id", it.id)
            }

            mFragment.arguments = bundle
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.editarTarefas, mFragment)
                .commit()

        }
        binding.TarefasNorte.adapter = adapter
        binding.TarefasNorte.layoutManager = LinearLayoutManager(this)
    }

    private fun apagandoTarefa(id: String){
        db.collection("tarefas")
            .document(id)
            .delete()
            .addOnSuccessListener { documentReference ->
                val intent = Intent(this, TarefasNorteActivity::class.java)
                startActivity(intent)
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Erro ao atualizar tarefa: ${exception.message}", Toast.LENGTH_LONG).show()
            }
    }

    private fun pegandoTarefasDoFirebase() {
        db.collection("tarefas")
            .get()
            .addOnCompleteListener { tarefas ->
                if (tarefas.isSuccessful && tarefas.result!= null) {
                    try {
                        for (document in tarefas.result!!) {
                            val id = document.id
                            var data = ""
                            var descricao = ""
                            var nomePraia = ""
                            var status = ""
                            var urlFoto = ""
                            Log.d("Firestore", "Documento: ${document.id}")
                            document.data.entries.map {
                                when (it.key) {
                                    "data" -> data = it.value.toString()
                                    "descricao" -> descricao = it.value.toString()
                                    "nomePraia" -> nomePraia = it.value.toString()
                                    "status" -> status = it.value.toString()
                                    "urlFoto" -> urlFoto = it.value.toString()
                                    else -> {}
                                }
                            }
                            TarefasLista.add(
                                Tarefas(
                                    id,
                                    nomePraia,
                                    descricao,
                                    data,
                                    urlFoto,
                                    status
                                )
                            )
                            adapter.notifyDataSetChanged()
                        }
                    } catch (e: Exception) {
                        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(this, "Erro ao carregar as tarefas", Toast.LENGTH_LONG).show()
                }
            }

    }

}
