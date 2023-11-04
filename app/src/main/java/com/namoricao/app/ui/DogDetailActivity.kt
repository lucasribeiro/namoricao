package com.namoricao.app.ui

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.namoricao.app.R
import com.namoricao.app.databinding.ActivityDogDetailBinding
import com.namoricao.app.model.Dog

class DogDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDogDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDogDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val dogId = intent.getIntExtra("dogId", -1) // Recupere o ID do cão do Intent

        if (dogId != -1) {
            // Recupere os dados do cão com base no ID e exiba-os no layout
            val dog = obterCaoPorId(dogId) // Substitua com seu método de obtenção de cão por ID
            exibirDadosDoCao(dog)
        }

    }

    private fun obterCaoPorId(dogId: Int): Dog {
        // Lê o arquivo JSON
        val json = resources.openRawResource(R.raw.dogs) // R.raw.dogs é o recurso do seu arquivo JSON

        val buffer = ByteArray(json.available())
        json.read(buffer)
        json.close()

        val jsonString = String(buffer, Charsets.UTF_8)

        // Converte o JSON em uma lista de objetos Dog
        val gson = Gson()
        val dogList: List<Dog> = gson.fromJson(jsonString, object : TypeToken<List<Dog>>() {}.type)

        // Obtém o email do usuário das SharedPreferences
        val sharedPreferences = getSharedPreferences("NamoricaoPreferences", Context.MODE_PRIVATE)
        val userEmail = sharedPreferences.getString("userEmail", "")

        val userDogs = dogList.filter { it.id == dogId }

        return  userDogs.first()
    }

    private fun exibirDadosDoCao(dog: Dog) {

        val resourceId = resources.getIdentifier(dog.image, "drawable", packageName)
        binding.imageViewDog.setImageResource(resourceId)

        binding.nomeTextView.text = dog.name
        binding.racaTextView.text = dog.breed
        binding.cidadeTextView.text = dog.city
        binding.phoneTextView.text = dog.phone
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // A ação de voltar foi acionada
                onBackPressed() // Retorna à atividade anterior
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}