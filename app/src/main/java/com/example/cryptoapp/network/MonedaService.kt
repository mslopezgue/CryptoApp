package com.example.cryptoapp.network


import com.example.cryptoapp.cliente.RetrofitHelper
import com.example.cryptoapp.model.DetalleMoneda
import com.example.cryptoapp.model.Moneda
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class MonedaService {
        private val retrofit = RetrofitHelper.obtenCliente()

        suspend fun getMonedas(): List<Moneda> {
            return withContext(Dispatchers.IO) {
                val response = retrofit.create(InterfaceService::class.java).getAll()
                response.body() ?: emptyList()
            }
        }

    }
    private val retrofit = RetrofitHelper.obtenCliente()


    suspend fun getMonedas(): Response<List<Moneda>> {
        return  retrofit.create(InterfaceService::class.java).
        }

    suspend fun getMoneda(id:String): Response<DetalleMoneda> {
        return  retrofit.create(InterfaceService::class.java).getDetail(id)
        }
    }
