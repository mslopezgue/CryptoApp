package com.example.cryptoapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.cryptoapp.database.CryptoApplication.Companion.prefs
import com.example.cryptoapp.repositorio.Repositorio

class MonedaViewModel : ViewModel() {
    private val repository = Repositorio()
    var monedas = repository.getAll().asLiveData()
    var detailMoneda = repository.getDetailMoneda(prefs.idMoneda!!).asLiveData()
}
