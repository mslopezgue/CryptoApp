package com.example.cryptoapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cryptoapp.dao.MonedaDao
import com.example.cryptoapp.dao.DetalleMonedaEntity
import com.example.cryptoapp.dao.MonedaEntity

@Database(
    entities = [MonedaEntity::class, DetalleMonedaEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DB : RoomDatabase() {
    abstract fun monedaDAO(): MonedaDao
}
