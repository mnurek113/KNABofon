package com.code.knab.knabofon.dagger

import com.code.knab.knabofon.dagger.AppModule
import com.code.knab.knabofon.ui.main.dagger.MainComponent
import com.code.knab.knabofon.ui.main.dagger.MainModule
import com.code.knab.knabofon.ui.transmition.dagger.TransmitionComponent
import com.code.knab.knabofon.ui.transmition.dagger.TransmitionModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class, BluetoothModule::class))
@Singleton
interface AppComponent {

    fun plus(mainModule: MainModule): MainComponent
    fun plus(transmitionModule: TransmitionModule) : TransmitionComponent
}