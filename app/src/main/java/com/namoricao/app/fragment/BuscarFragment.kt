package com.namoricao.app.fragment

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.namoricao.app.viewmodel.BuscarViewModel
import com.namoricao.app.R
import com.namoricao.app.adaptors.DogItemDecoration
import com.namoricao.app.adaptors.DogListAdapter
import com.namoricao.app.adaptors.Screen
import com.namoricao.app.databinding.ActivityLoginBinding
import com.namoricao.app.model.Dog

class BuscarFragment : Fragment() {

    companion object {
        fun newInstance() = BuscarFragment()
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: BuscarViewModel
    private lateinit var racaText: TextView
    private lateinit var dogCount: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_buscar, container, false)

        val findButton = view.findViewById<Button>(R.id.buttonFind)

        dogCount = view.findViewById<TextView>(R.id.textViewDogCount)
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewDogs)
        racaText = view.findViewById<TextView>(R.id.buscarEditText)


        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing_between_items) // 20 pixels
        val dividerHeightInPixels = resources.getDimensionPixelSize(R.dimen.divider_height) // Altura da linha, em pixels
        val dividerColor = ContextCompat.getColor(requireContext(), R.color.divider_color) // Cor da linha
        recyclerView.addItemDecoration(DogItemDecoration(spacingInPixels, dividerHeightInPixels, dividerColor))

        findButton.setOnClickListener {
            onAdicionarButtonClick()
        }

        return view
    }

    fun onAdicionarButtonClick() {

        var dogsList = BuscarCaes(racaText.text.toString())

        if (dogsList.count() == 1) {
            dogCount.text = dogsList.count().toString() + " cão"
        }
        else {
            dogCount.text = dogsList.count().toString() + " cães"
        }

        val adapter = DogListAdapter(requireContext(), dogsList, Screen.BUSCA)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(BuscarViewModel::class.java)
    }

    fun BuscarCaes(raca: String): List<Dog> {

        // Lê o arquivo JSON
        val json = resources.openRawResource(R.raw.dogs)

        val buffer = ByteArray(json.available())
        json.read(buffer)
        json.close()

        val jsonString = String(buffer, Charsets.UTF_8)

        // Converte o JSON em uma lista de objetos Dog
        val gson = Gson()
        val dogList: List<Dog> = gson.fromJson(jsonString, object : TypeToken<List<Dog>>() {}.type)

        return dogList.filter { it.breed == raca }
    }

}