package com.code.knab.knabofon.dagger

import android.bluetooth.BluetoothAdapter
import com.code.knab.knabofon.bluetooth.BluetoothFacade
import com.code.knab.knabofon.bluetooth.BluetoothFacadeImpl
import com.code.knab.knabofon.ui.transmition.connection_service.BluetoothConnectionService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BluetoothModule {

    @Provides
    @Singleton
    fun provideBluetoothFacade() : BluetoothFacade =
            BluetoothFacadeImpl(BluetoothAdapter.getDefaultAdapter())

    @Provides
    @Singleton
    fun provideBluetoothConnectionService() : BluetoothConnectionService =
            BluetoothConnectionService()
}