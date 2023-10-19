package com.namoricao.app.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.namoricao.app.PrincipalViewModel
import com.namoricao.app.R

class PrincipalFragment : Fragment() {

    companion object {
        fun newInstance() = PrincipalFragment()
    }

    private lateinit var viewModel: PrincipalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_principal, container, false)



        // Inicialize o ViewModel
        viewModel = ViewModelProvider(this).get(PrincipalViewModel::class.java)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PrincipalViewModel::class.java)
        // TODO: Use the ViewModel
    }

}