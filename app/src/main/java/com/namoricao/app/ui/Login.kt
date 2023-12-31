package com.namoricao.app.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.namoricao.app.R
import android.widget.Button
import android.widget.Toast
import com.namoricao.app.databinding.ActivityLoginBinding
import com.google.gson.Gson
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
                    // As credenciais são válidas, redirecione para a MeusCaesActivity

                    // Obtém uma referência às SharedPreferences
                    val sharedPreferences = getSharedPreferences("NamoricaoPreferences", Context.MODE_PRIVATE)

                    // Obtém um editor para modificar as SharedPreferences
                    val editor = sharedPreferences.edit()

                    // Salva o email do usuário nas SharedPreferences
                    editor.putString("userEmail", emailDigitado)
                    editor.apply()

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
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