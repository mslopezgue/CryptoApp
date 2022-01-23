package com.example.cryptoapp.repositorio


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val service =RetrofitHelper.obtenCliente()
    var lista = MutableLiveData<List<Moneda>>()

    fun obtenerNoticias() {
        val call = service.create()baseUrl()
        call.enqueue(object : Callback<Moneda> {
            override fun onResponse(call: Call<Moneda>, response: Response<Moneda>)
            {
                CoroutineScope(Dispatchers.IO).launch {
                    response.body()?.let {
                        Log.v("logenrepo", response.body().toString())
                        monedaDao.agregar(it)
                    }
                }
            }
            override fun onFailure(call: Call<Moneda>, t: Throwable) {
                call.cancel()
            }
        })
    }
    fun traerDataBD(): LiveData<List<MonedaEntity>> {
        return monedaDao.listar()
    }
    fun busquedaRepo(noticia: String, idioma: String, apikey: String) {

        val call = service.buscarNoticias(noticia, idioma, apikey)
        call.enqueue(object : Callback<NoticiaResponse> {

            override fun onResponse(call: Call<NoticiaResponse>, response: Response<NoticiaResponse>) {
                CoroutineScope(Dispatchers.IO).launch {
                    Log.v("busquedaRepo",lista .value.toString())
                    lista.postValue(response.body()?.articles)
                }
            }

            override fun onFailure(call: Call<NoticiaResponse>, t: Throwable) {
                call.cancel()

            }
        })

    }

    fun mostrarBusquedaEnRepo(): MutableLiveData<List<Articulo>> {
        Log.v("TraerBusquedaRepo", lista.toString())
        return lista
    }
}

}