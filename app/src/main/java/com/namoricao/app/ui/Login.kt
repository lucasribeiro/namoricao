package com.namoricao.app.ui

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.namoricao.app.R
import android.widget.Button
import android.widget.Toast
import com.namoricao.app.databinding.ActivityLoginBinding
import org.json.JSONObject
import com.google.gson.Gson
import com.namoricao.app.model.Usuario
import com.namoricao.app.model.Usuarios

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val buttonLogin = findViewById<Button>(R.id.buttonLogin)

        buttonLogin.setOnClickListener {
            val emailDigitado = binding.emailEditText.text.toString()
            val senhaDigitada = binding.senhaEditText.text.toString()

            if (!isEmailValido(emailDigitado)) {
                Toast.makeText(this, "Email inválido", Toast.LENGTH_SHORT).show()
            }

            val jsonContent = resources.openRawResource(R.raw.users).bufferedReader().use {
                it.readText()
            }

            try {
                val jsonContent = resources.openRawResource(R.raw.users).bufferedReader().use {
                    it.readText()
                }

                val gson = Gson()
                val usuarios = gson.fromJson(jsonContent, Usuarios::class.java)

                val usuarioEncontrado = usuarios.usuarios.find {
                    it.email == emailDigitado && it.senha == senhaDigitada
                }

                if (usuarioEncontrado != null) {
                    // As credenciais são válidas, você pode fazer o que for necessário aqui
                    // Por exemplo, iniciar uma nova atividade ou exibir uma mensagem de sucesso
                    // Após a autenticação bem-sucedida.
                    Toast.makeText(this, "Sucesso!!!", Toast.LENGTH_SHORT).show()
                } else {
                    // Se as credenciais não correspondem a nenhum usuário no arquivo JSON,
                    // exiba um Toast com a mensagem de erro.
                    Toast.makeText(this, "E-mail ou senha inválido", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Método para fechar o aplicativo
    fun fecharApp(view: View) {
        finish() // Fecha a atividade atual, o que também encerra o aplicativo
    }

    private fun isEmailValido(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}