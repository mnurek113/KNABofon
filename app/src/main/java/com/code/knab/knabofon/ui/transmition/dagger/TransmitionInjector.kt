package com.code.knab.knabofon.ui.transmition.dagger

import com.code.knab.knabofon.KNABofonApp
import com.code.knab.knabofon.dagger.Injector
import com.code.knab.knabofon.ui.transmition.TransmitionActivity

class TransmitionInjector : Injector<TransmitionActivity> {
    override fun inject(target: TransmitionActivity) {
        (target.applicationContext as KNABofonApp).component.plus(TransmitionModule(target)).inject(target)
    }


}