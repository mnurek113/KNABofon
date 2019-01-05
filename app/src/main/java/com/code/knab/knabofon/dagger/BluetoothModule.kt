package com.code.knab.knabofon.dagger

import android.bluetooth.BluetoothAdapter
import com.code.knab.knabofon.bluetooth.BluetoothFacade
import com.code.knab.knabofon.bluetooth.BluetoothFacadeImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BluetoothModule {

    @Provides
    @Singleton
    fun provideBluetoothFacade() : BluetoothFacade = BluetoothFacadeImpl(BluetoothAdapter.getDefaultAdapter())
}