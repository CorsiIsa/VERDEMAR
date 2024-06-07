package com.example.verdemar

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.verdemar.databinding.ActivityCadastrofisicoBinding
import com.example.verdemar.databinding.ActivityCadastrojuridicoBinding
import com.google.firebase.auth.FirebaseAuth

class CadastroJuridicoActivity: AppCompatActivity()   {

    private lateinit var binding : ActivityCadastrojuridicoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastrojuridicoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCadastrarPessoaJuridica.setOnClickListener(){
            validacaoDados()
        }
    }

    private fun validacaoDados() {
        val nome = binding.Nome.text.toString()
        val email = binding.Email.text.toString()
        val nrCNPJ = binding.NrCnpj.text.toString()
        val inscricaoEstadual = binding.InscEstadual.text.toString()
        val razaoSocial = binding.RazaoSocial.text.toString()
        val senha = binding.Senha.text.toString()

        if (nome.isEmpty() || email.isEmpty() || nrCNPJ.isEmpty() ||
            inscricaoEstadual.isEmpty() || razaoSocial.isEmpty() || senha.isEmpty() ||
            senha.length < 6) {
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_LONG).show()
        } else {
            salvandoOsDados(nome, email, nrCNPJ, inscricaoEstadual, razaoSocial, senha)
        }

    }

    private fun salvandoOsDados(nome: String, email: String, nrCNPJ: String, inscricaoEstadual: String, razaoSocial: String, senha: String) {
        val sharedPreferences = getSharedPreferences("dados", AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("name", nome)
        editor.putString("email", email)
        editor.putString("cnpj", nrCNPJ)
        editor.putString("inscricao", inscricaoEstadual)
        editor.putString("razao", razaoSocial)
        editor.putString("senha", senha)
        editor.apply()


        registrandoUsuarioNoFirebase(email,senha)
        Toast.makeText(this,
            "Dados salvo com sucesso!",
            Toast.LENGTH_SHORT
        ).show()

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun registrandoUsuarioNoFirebase(email: String, senha: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this,
                        "Usuário registrado com sucesso!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(this,
                        "Falha de Firebase ${task.exception?.message}",
                        Toast.LENGTH_LONG)
                        .show()
                }
            }
    }

}