package com.namoricao.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.namoricao.app.R

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



        // Define um OnClickListener para o ícone da Toolbar
        toolbar.setNavigationOnClickListener {
            // Abre o menu lateral quando o ícone é clicado
            drawerLayout.openDrawer(GravityCompat.START)
        }


        toolbar.navigationIcon = null

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PrincipalFragment())
                .commit()
        }
    }

    // Inflar o menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    // Lidar com as ações do menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_buscar -> {
                // Lógica para ação "Buscar"
                return true
            }
            R.id.menu_meus_caes -> {
                // Lógica para ação "Meus Cães"
                return true
            }
            R.id.menu_sair -> {
                // Lógica para ação "Sair"
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}