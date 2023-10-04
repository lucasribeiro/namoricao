package com.namoricao.app.ui

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.namoricao.app.R
import android.widget.Button
import com.namoricao.app.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val buttonLogin = findViewById<Button>(R.id.buttonLogin)

        buttonLogin.setOnClickListener {
            // Obter os valores dos campos de login e senha
            //val login = binding.editTextLogin.text.toString()
            //val senha = binding.editTextPassword.text.toString()

            // Você pode adicionar validações de login e senha aqui
            // Por exemplo, verificar se o login e a senha são válidos

            // Se a validação for bem-sucedida, você pode iniciar a próxima atividade
            //val intent = Intent(this, MainActivity::class.java)
            //startActivity(intent)
        }
    }
}