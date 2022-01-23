package com.example.cryptoapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.cryptoapp.database.CryptoApplication
import com.example.cryptoapp.database.CryptoApplication.Companion.prefs
import com.example.cryptoapp.model.Moneda
import com.example.cryptoapp.repositorio.Repositorio

class MonedaViewModel : ViewModel() {
    private val repositorio = Repositorio()

    fun listaMonedas() {
        Log.v("expone traemeLaListaDeArticulosDelServer", repositorio.getAll().toString())
        repositorio.getAll()

    }

    fun detalleMoneda(): MutableLiveData<List<Moneda>> {

        Log.v("expone exponeNoticiasDeLaApi_EnVM", repositorio.getDetailMoneda(prefs.idMoneda!!).value.toString())
            return repositorio.getDetailMoneda()
    }


    var monedas = repositorio.getAll().asLiveData()
    var detailMoneda = repositorio.getDetailMoneda(prefs.idMoneda!!).asLiveData()
}