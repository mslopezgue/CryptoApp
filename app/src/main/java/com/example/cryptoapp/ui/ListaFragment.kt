package com.example.cryptoapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptoapp.R
import com.example.cryptoapp.adapter.MonedaAdapter
import com.example.cryptoapp.MonedaViewModel
import com.example.cryptoapp.databinding.FragmentListaBinding
import com.example.cryptoapp.model.Moneda


class ListaFragment : Fragment() {
    private var b: FragmentListaBinding? = null
    private val binding get() = b!!
    private lateinit var monedaViewModel: MonedaViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = FragmentListaBinding.inflate(inflater, container, false)

        monedaViewModel = ViewModelProvider(this).get(MonedaViewModel::class.java)
        val adapter = MonedaAdapter()

        with(binding)
        {
            rvLista.layoutManager = LinearLayoutManager(context)
            rvLista.adapter = adapter

             adapter.setOnItemClickListener(object : MonedaAdapter.OnClickListener {
                override fun onClickItem(moneda: Moneda) {


                    var miBundle = Bundle()
                    miBundle.putSerializable("moneda", moneda)

                    findNavController().navigate(R.id.action_listaFragment_to_articuloFragment, miBundle)
                }
            })
        }

        monedaViewModel.exponeDatosDeDB().observe(viewLifecycleOwner, Observer {

            Log.v("RecyclerViewMoneda", it.toString())
            adapter.setMoneda(it)

        })

        return binding.root

    }
}