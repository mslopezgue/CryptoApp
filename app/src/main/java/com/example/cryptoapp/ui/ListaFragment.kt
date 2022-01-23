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
    private var _binding: FragmentListaBinding? = null
    private val binding get() = _binding!!
    private lateinit var vmodel: MonedaViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListaBinding.inflate(inflater, container, false)

        vmodel = ViewModelProvider(this).get(MonedaViewModel::class.java)

        //val recycler = binding.rvNoticias
        val adapter = MonedaAdapter()

        //recycler.layoutManager = LinearLayoutManager(requireContext())
        with(binding)
        {
            rvLista.layoutManager = LinearLayoutManager(context)
            rvLista.adapter = adapter
            vmodel.monedas()
            adapter.setOnItemClickListener(object : MonedaAdapter.OnClickListener {
                override fun alClickearItem(moneda: Moneda) {
                    //Listener para click en los objetos de la lista

                    var miBundle = Bundle()
                    miBundle.putSerializable("moneda", moneda)

                    findNavController().navigate(R.id.action_listaFragment_to_detalleFragment, miBundle)
                }
            })


        }

        vmodel.exponeNoticiasDeLaApi_EnVM().observe(viewLifecycleOwner, Observer {

            Log.v("RecyclerViewNoticias", it.toString())
            adapter.setMoneda(it)

        })

         return binding.root
    }
}