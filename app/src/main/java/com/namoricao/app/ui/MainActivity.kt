package com.namoricao.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.namoricao.app.R
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
                Toast.makeText(this, "Email inválido", Toast.LENGTH_SHORT).show()
                return true
                /*
                val navigationView = findViewById<NavigationView>(R.id.nav_view)
                navigationView.setNavigationItemSelectedListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.nav_my_dogs -> {
                            val fragmentManager = supportFragmentManager
                            val transaction = fragmentManager.beginTransaction()
                            transaction.replace(R.id.fragment_container, DogListFragment())
                            transaction.commit()
                        }
                        // Outros itens de menu
                    }
                    val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
                    drawer.closeDrawer(GravityCompat.START)
                    true
                }

                 */
            }
            R.id.menu_sair -> {
                // Lógica para ação "Sair"
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}