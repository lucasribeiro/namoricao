package com.namoricao.app.fragment

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.namoricao.app.viewmodel.DogListViewModel
import com.namoricao.app.R
import com.namoricao.app.adaptors.DogItemDecoration
import com.namoricao.app.adaptors.DogListAdapter
import com.namoricao.app.adaptors.Screen
import com.namoricao.app.model.Dog

class DogListFragment : Fragment() {

    companion object {
        fun newInstance() = DogListFragment()
    }

    private lateinit var viewModel: DogListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dog_list, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewDogs)
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing_between_items) // 20 pixels
        val dividerHeightInPixels = resources.getDimensionPixelSize(R.dimen.divider_height) // Altura da linha, em pixels
        val dividerColor = ContextCompat.getColor(requireContext(), R.color.TextColor) // Cor da linha
        recyclerView.addItemDecoration(DogItemDecoration(spacingInPixels, dividerHeightInPixels, dividerColor))

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
        val sharedPreferences = requireContext().getSharedPreferences("NamoricaoPreferences", Context.MODE_PRIVATE)
        val userEmail = sharedPreferences.getString("userEmail", "")

        val userDogs = dogList.filter { it.email == userEmail }
        val DogCount = view.findViewById<TextView>(R.id.textViewDogCount)
        DogCount.text = userDogs.count().toString() + " cães"

        val adapter = DogListAdapter(requireContext(), userDogs, Screen.MEUS_CAES)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DogListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}