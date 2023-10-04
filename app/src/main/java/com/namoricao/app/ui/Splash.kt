package com.namoricao.app.ui

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.namoricao.app.R
import android.content.Intent
import android.os.Handler

class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Define o tempo de espera em milissegundos (5 segundos)
        val splashTimeOut: Long = 5000

        // Cria um Handler para atrasar a abertura da tela de login
        Handler().postDelayed({
            // Intent para a tela de login
            val intent = Intent(this, Login::class.java)
            startActivity(intent)

            // Fecha a tela de splash
            finish()
        }, splashTimeOut)
    }
}