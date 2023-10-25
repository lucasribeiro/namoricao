package com.namoricao.app.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.namoricao.app.viewmodel.DogListViewModel
import com.namoricao.app.R
import com.namoricao.app.adaptors.DogItemDecoration
import com.namoricao.app.adaptors.DogListAdapter
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
        val dividerColor = ContextCompat.getColor(requireContext(), R.color.divider_color) // Cor da linha
        recyclerView.addItemDecoration(DogItemDecoration(spacingInPixels, dividerHeightInPixels, dividerColor))

        val dogList = listOf(
            Dog("Cachorro 1", "Raça 1", R.drawable.logo),
            Dog("Cachorro 2", "Raça 2", R.drawable.logo),
            Dog("Cachorro 3", "Raça 3", R.drawable.logo),
            Dog("Cachorro 1", "Raça 4", R.drawable.logo),
            Dog("Cachorro 2", "Raça 5", R.drawable.logo),
            Dog("Cachorro 3", "Raça 6", R.drawable.logo)
            // Adicione mais cães conforme necessário
        )

        val adapter = DogListAdapter(requireContext(), dogList)
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