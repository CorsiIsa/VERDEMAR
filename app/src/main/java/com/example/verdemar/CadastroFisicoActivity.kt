package com.example.verdemar

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.verdemar.databinding.ActivityCadastrofisicoBinding
import com.example.verdemar.databinding.ActivityMainBinding
import com.example.verdemar.databinding.ActivityTipocadastroBinding
import com.google.firebase.auth.FirebaseAuth

class CadastroFisicoActivity : AppCompatActivity()   {
    private lateinit var binding : ActivityCadastrofisicoBinding
    private lateinit var sexo: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastrofisicoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rbFeminino.setOnClickListener(){
            sexo= "F"
        }
        binding.rbMaculino.setOnClickListener(){
            sexo = "M"
        }
        binding.btnCadastrar.setOnClickListener(){
            validacaoDados()
        }
    }

    private fun validacaoDados() {
        val nome = binding.Nome.text.toString()
        val email = binding.Email.text.toString()
        val dtNacimento = binding.DataNascimento.text.toString()
        val escolaridade = binding.Escolaridade.text.toString()
        val profissao = binding.Profissao.text.toString()
        val cpf = binding.Cpf.text.toString()
        val rg = binding.Rg.text.toString()
        val senha = binding.Senha.text.toString()


        if (nome.isEmpty() || email.isEmpty() || dtNacimento.isEmpty() ||
            cpf.isEmpty() || rg.isEmpty() || senha.isEmpty() ||
            senha.length < 6) {
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_LONG).show()
        } else {
            salvandoOsDados(nome, email, dtNacimento, escolaridade, profissao, cpf, rg, senha)
        }

    }

    //A intenção desse método era salvar para exibir no Perfil, mas não deu tempo de criar a tela Perfil
    private fun salvandoOsDados(nome: String, email: String, dtNacimento: String, escolaridade: String, profissao: String, cpf: String, rg: String, senha: String) {
        val sharedPreferences = getSharedPreferences("dados", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("name", nome)
        editor.putString("email", email)
        editor.putString("dtNacimento", dtNacimento)
        editor.putString("sexo", sexo)
        editor.putString("escolaridade", escolaridade)
        editor.putString("profissao", profissao)
        editor.putString("cpf", cpf)
        editor.putString("rg", rg)
        editor.putString("senha", senha)
        editor.apply()
        registrandoUsuarioNoFirebase(email,senha)
        Toast.makeText(this, "Dados salvo com sucesso!", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)


    }

    fun registrandoUsuarioNoFirebase(email: String, senha: String) {
        Toast.makeText(this, senha, Toast.LENGTH_SHORT).show()
        Toast.makeText(this, email, Toast.LENGTH_SHORT).show()
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Usuário registrado com sucesso!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Falha de Firebase ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }

}