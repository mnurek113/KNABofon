package com.code.knab.knabofon.ui.main.mvp

import com.code.knab.knabofon.utils.rx.RxUtils

class MainPresenter(private val view: MainMVP.View,
                    private val model: MainMVP.Model,
                    private val rxUtils: RxUtils) : MainMVP.Presenter {

    override fun btEnableDisable() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun btEnableDiscovering() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}