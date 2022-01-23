package com.example.cryptoapp.repositorio


import android.util.Log
import com.example.cryptoapp.dao.DetalleMonedaEntity
import com.example.cryptoapp.dao.MonedaDao
import com.example.cryptoapp.dao.MonedaEntity
import com.example.cryptoapp.database.CryptoApplication.Companion.db
import com.example.cryptoapp.database.MonedaConverters
import com.example.cryptoapp.model.DetalleMoneda
import com.example.cryptoapp.model.Moneda
import com.example.cryptoapp.network.MonedaService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class Repositorio {
    private val monedaService = MonedaService()

    fun getAll(): Flow<List<Moneda>> = flow {
    while(true)
    {
        val monedaResponse = kotlin.runCatching { monedaService.getMonedas() }

        monedaResponse.onSuccess {
            if(it.body()!=null)
            {
                db.monedaDAO().insertMonedas(MonedaConverters.converterMonedasEntity(it.body()!!))
            }
        }

        monedaResponse.onFailure {
            Log.d("Error Moneda", it.toString())
        }

        val monedasEntity: List<MonedaEntity> = db.monedaDAO().getAll()

        if(monedasEntity!=null)
        {
            emit(MonedaConverters.converterMonedas(monedasEntity))
        }

        delay(5000)
    }

}.flowOn(Dispatchers.IO)

fun getDetailMoneda(id:String): Flow<DetalleMoneda> = flow {
    while(true)
    {
        val monedaResponse = kotlin.runCatching { monedaService.getMoneda(id) }

        monedaResponse.onSuccess {
            Log.d("DetalleMoneda", it.body().toString())
            if(it.body()!=null)
            {
                db.monedaDAO().insertDetailMoneda(MonedaConverters.converterDetailMonedaEntity(it.body()!!))
            }
        }

        monedaResponse.onFailure {
            Log.d("ErrorDetalleMoneda", it.toString())
        }

        val detailMonedaEntity: DetalleMonedaEntity = db.monedaDAO().getDetailMoneda(id)

        if(detailMonedaEntity!=null)
        {
            emit(MonedaConverters.converterDetailMoneda(detailMonedaEntity))
        }

        delay(5000)
    }

}.flowOn(Dispatchers.IO)
}