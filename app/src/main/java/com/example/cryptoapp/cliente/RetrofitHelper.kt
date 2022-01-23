package com.example.cryptoapp.cliente

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {
    companion object {

        const val URL_BASE = "https://fake-server-app-crypto.herokuapp.com/"

        fun obtenCliente(): Retrofit {
            return Retrofit.Builder().baseUrl(URL_BASE).addConverterFactory(
                GsonConverterFactory.create()
            ).build()
        }
    }
}