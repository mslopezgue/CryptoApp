package com.example.cryptoapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.jetbrains.annotations.NotNull

    @Dao
    interface MonedaDao {
        @Query("SELECT * FROM moneda_entity")
        suspend fun getAll(): List<MonedaEntity>

        @Query("SELECT * FROM detalle_moneda_entity WHERE detalle_moneda_entity.id =:id")
        suspend fun getDetailMoneda(@NotNull id: String): DetalleMonedaEntity

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertMonedas(@NotNull monedas:List<MonedaEntity>)

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertDetailMoneda(@NotNull detailMoneda:DetalleMonedaEntity)
    }

