package com.code.knab.knabofon.ui.transmition.mvp

import android.bluetooth.BluetoothDevice
import android.util.Log
import com.code.knab.knabofon.utils.rx.RxUtils

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

/**
 * Created by hp on 2017-09-08.
 */

class TPresenter(private val view: TransmitionMVP.View,
                 private val model: TransmitionMVP.Model,
                 private val rxUtils: RxUtils) : TransmitionMVP.Presenter {

    private var string = ""
    private val compositeDisposable = CompositeDisposable()

    override fun initConnectionListener() {
        model.startConnectionService()
    }

    override fun connect(pairedDevice: BluetoothDevice) {
        if (model.attemptConnection(pairedDevice))
            view.onSuccessfulConnection()
    }

    override fun sendMessage(message: String) {
        model.sendByConnectionService(message)
    }

    override fun startReading() {

        compositeDisposable.add(
                model.readFromConnectionService()
                        .subscribeOn(rxUtils.subscribeScheduler)
                        .observeOn(rxUtils.observScheduler)
                        .subscribeWith(MessageObserver())
        )


    }


    override fun closeConnection() {
        model.cancelConnectionService()
    }

    fun clearDisposables() {
        Log.d(TAG, "clearDisposables called.")
        compositeDisposable.dispose()
    }

    private inner class MessageObserver : DisposableObserver<String>() {

        override fun onNext(message: String) {
            if (message != "") {
                Log.d(TAG, "Observer: onNext called.")
                string = string + message
            }
            view.showMessage(string)
            string = ""
        }

        override fun onError(e: Throwable) {
            Log.d(TAG, "Observer: onError called.")
        }

        override fun onComplete() {

        }
    }

    companion object {
        private val TAG = "TPresenter"
    }
}
