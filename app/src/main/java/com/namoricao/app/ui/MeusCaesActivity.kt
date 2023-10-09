package com.namoricao.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.namoricao.app.R
import com.namoricao.app.databinding.ActivityMeusCaesBinding


class MeusCaesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMeusCaesBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMeusCaesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar a Toolbar
        setSupportActionBar(binding.toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        // Configurar o Navigation Drawer
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MeusCaesFragment.newInstance())
                .commitNow()
        }
    }

    // Sobrescreva o método onBackPressed para fechar o Navigation Drawer ao pressionar o botão Voltar
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(navView)) {
            drawerLayout.closeDrawer(navView)
        } else {
            super.onBackPressed()
        }
    }
}