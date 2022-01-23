package com.example.cryptoapp.repositorio


import android.util.Log
import androidx.lifecycle.LiveData
import com.example.cryptoapp.cliente.RetrofitHelper
import com.example.cryptoapp.dao.MonedaDao
import com.example.cryptoapp.dao.MonedaEntity
import com.example.cryptoapp.model.Moneda
import com.example.cryptoapp.network.MonedaService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repositorio(private val monedaDao: MonedaDao) {

    private val service = RetrofitHelper.obtenCliente()
   // val miLiveData = monedaDao.getAll()
    fun getMonedas() {
         val call = service.()
        call.enqueue(object : Callback<List<Moneda>> {
            override fun onResponse(
                call: Call<List<Moneda>>,
                response: Response<List<Moneda>
            ) {
                CoroutineScope(Dispatchers.IO).launch {
                    response.body()?.let {
                        Log.v("logenrepo", response.body().toString())
                        monedaDao.insertMonedas(it)                    }
                }
            }

            override fun onFailure(call: Call<List<Moneda>>, t: Throwable) {
                call.cancel()
            }

        })
    }

    fun exponeDatosDelBaseDeDatos(): List<MonedaEntity> {
        return monedaDao.getAll()
    }
}