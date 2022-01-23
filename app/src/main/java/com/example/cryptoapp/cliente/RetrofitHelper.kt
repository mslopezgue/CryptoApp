package com.example.cryptoapp.cliente

import com.example.cryptoapp.model.DetalleMoneda
import com.example.cryptoapp.model.Moneda
import com.example.cryptoapp.network.InterfaceService
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {

    companion object {

        const val URL_BASE = "https://fake-server-app-crypto.herokuapp.com/"

        fun obtenCliente(): InterfaceService {
            val retrofit = Retrofit.Builder().baseUrl(URL_BASE).addConverterFactory(
                GsonConverterFactory.create()
            ).build()
            return retrofit.create(InterfaceService::class.java)

        fun getMonedas(): Response<List<Moneda>> {
        return  retrofit.create(InterfaceService::class.java).
    }

        fun getMoneda(id:String): Response<DetalleMoneda> {
        return  retrofit.create(InterfaceService::class.java).getDetail(id)
    }
}
