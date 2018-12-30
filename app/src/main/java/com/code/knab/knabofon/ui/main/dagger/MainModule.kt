package com.code.knab.knabofon.ui.main.dagger

import com.code.knab.knabofon.bluetooth.BluetoothFacade
import com.code.knab.knabofon.ui.main.mvp.MainMVP
import com.code.knab.knabofon.ui.main.mvp.MainModel
import com.code.knab.knabofon.ui.main.mvp.MainPresenter
import com.code.knab.knabofon.utils.rx.RxUtils
import dagger.Module
import dagger.Provides

@Module
class MainModule(private val view: MainMVP.View) {

    @Provides
    @MainScope
    fun providePresenter(model: MainMVP.Model, rxUtils: RxUtils) : MainMVP.Presenter =
            MainPresenter(view, model, rxUtils)

    @Provides
    @MainScope
    fun provideModel(bluetoothFacade: BluetoothFacade) : MainMVP.Model =
            MainModel(bluetoothFacade)

}