package com.example.verdemar

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.verdemar.databinding.FragmentEditarTarefaBinding
import com.google.firebase.firestore.FirebaseFirestore


class EditarTarefaFragment : Fragment() {
    private lateinit var binding: FragmentEditarTarefaBinding
    private val db = FirebaseFirestore.getInstance()
    private var tarefaSelecionada: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditarTarefaBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {

            tarefaSelecionada = it.getString("id")
            tarefaSelecionada?.let {
                inserindoDadosTarefas()
            }
        }

    }
    private fun inserindoDadosTarefas() {
        var nomePraia = ""
        var desc = ""
        var dtTarefa = ""
        var urlFoto = ""
        var status = "Ativa"
        binding.NomeTarefaEditada.doOnTextChanged { text, start, before, count ->
            nomePraia = text.toString()
        }
        binding.DescTarefaEditada.doOnTextChanged { text, start, before, count ->
            desc = text.toString()
        }
        binding.DataTarefaEditada.doOnTextChanged { text, start, before, count ->
            dtTarefa = text.toString()
        }

        binding.btnSalvaTarefa.setOnClickListener {
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
            .document(tarefaSelecionada.orEmpty())
            .set(tarefa)
            .addOnSuccessListener { documentReference ->
                val intent = Intent(requireContext(), TarefasNorteActivity::class.java)
                startActivity(intent)
            }
            .addOnFailureListener { exception ->
                Toast.makeText(requireContext(), "Erro ao atualizar tarefa: ${exception.message}", Toast.LENGTH_LONG).show()
            }
    }
}