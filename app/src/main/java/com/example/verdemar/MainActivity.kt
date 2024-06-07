package com.example.verdemar

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.verdemar.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private lateinit var emailLogin: String
    private lateinit var senhaLogin: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnEntrar.setOnClickListener(){
            emailLogin = binding.txtEmailLogin.text.toString()
            senhaLogin = binding.txtSenhaLogin.text.toString()
            validandoNoFirebase(emailLogin, senhaLogin)
        }
        binding.txtRegistrar.setOnClickListener(){
            val intent = Intent(this, TipoCadastroActivity::class.java)
            startActivity(intent)
        }
    }
    fun validandoNoFirebase(email: String, senha: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, TelaInicialActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        this, "Ao tentar realizar o login ${task.exception?.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }
}


