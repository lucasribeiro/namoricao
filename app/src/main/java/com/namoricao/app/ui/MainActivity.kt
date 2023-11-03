package com.namoricao.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.namoricao.app.R
import com.namoricao.app.adaptors.Screen
import com.namoricao.app.fragment.BuscarFragment
import com.namoricao.app.fragment.DogListFragment
import com.namoricao.app.fragment.PrincipalFragment

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)

        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.menu)

        setupNavigationView()

        inflarFragment(Screen.MEUS_CAES)
/*
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PrincipalFragment())
                .commit()
        }

 */
    }

    // Inflar o menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    private fun inflarFragment(fragmento: Screen)
    {
        val tela = when (fragmento) {
            Screen.MEUS_CAES -> DogListFragment()
            Screen.BUSCA -> BuscarFragment()
            else -> DogListFragment()
        }

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.container, tela)
        transaction.commit()
    }

    private fun setupNavigationView() {

        val navigationView = findViewById<NavigationView>(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_meus_caes -> {
                    inflarFragment(Screen.MEUS_CAES)
                }
                R.id.menu_buscar -> {
                    inflarFragment(Screen.BUSCA)
                }
                R.id.menu_sair -> {
                    finishAffinity() // Fecha todas as atividades e encerra o aplicativo
                    true // Indica que o item de menu foi manipulado
                }
                // Outros itens de menu
            }

            val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
            drawer.closeDrawer(GravityCompat.START)
            true
        }
    }
}