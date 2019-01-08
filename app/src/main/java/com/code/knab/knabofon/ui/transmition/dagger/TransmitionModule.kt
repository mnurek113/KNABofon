package com.code.knab.knabofon.ui.transmition.dagger

import com.code.knab.knabofon.bluetooth.BluetoothFacade
import com.code.knab.knabofon.ui.transmition.connection_service.BluetoothConnectionService
import com.code.knab.knabofon.ui.transmition.mvp.TModel
import com.code.knab.knabofon.ui.transmition.mvp.TPresenter
import com.code.knab.knabofon.ui.transmition.mvp.TransmitionMVP
import com.code.knab.knabofon.utils.rx.RxUtils
import dagger.Module
import dagger.Provides

@Module
class TransmitionModule(private val view: TransmitionMVP.View) {

    @Provides
    @TransmitionScope
    fun providePresenter(model: TransmitionMVP.Model,
                         rxUtils: RxUtils) : TransmitionMVP.Presenter =
            TPresenter(view, model, rxUtils)

    @Provides
    @TransmitionScope
    fun provideModel(bluetoothFacade: BluetoothFacade, bluetoothConnectionService: BluetoothConnectionService) : TransmitionMVP.Model =
            TModel(bluetoothFacade, bluetoothConnectionService)

}

