package com.example.cryptoapp.network

import com.example.cryptoapp.model.DetalleMoneda
import com.example.cryptoapp.model.Moneda
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

class InterfaceService {

    interface InterfaceService {
        @GET("general/")
        suspend fun getAll(): Response<List<Moneda>>

        @GET("details/{id}")
        suspend fun getDetail(@Path("id") id:String) : Response<DetalleMoneda>
    }
}