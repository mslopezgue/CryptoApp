package com.example.cryptoapp.database

import android.app.Application
import androidx.room.Room
import com.example.cryptoapp.shared.Prefs

class CryptoApplication: Application() {
    companion object{
        lateinit var db: DB
        lateinit var prefs: Prefs
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            this,
            DB::class.java,
            "cryptoapp"
        ).build()

        prefs = Prefs(applicationContext)
    }
}
