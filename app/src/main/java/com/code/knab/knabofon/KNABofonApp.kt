package com.code.knab.knabofon

import android.app.Application
import com.code.knab.knabofon.dagger.AppModule
import com.code.knab.knabofon.dagger.BluetoothModule
import com.code.knab.knabofon.dagger.DaggerAppComponent

class KNABofonApp: Application() {

    val component by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .bluetoothModule(BluetoothModule())
                .build()
    }

    override fun onCreate() {
        super.onCreate()
    }

}