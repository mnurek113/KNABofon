package com.code.knab.knabofon.ui.main.dagger

import com.code.knab.knabofon.KNABofonApp
import com.code.knab.knabofon.dagger.Injector
import com.code.knab.knabofon.ui.main.MainActivity

class MainInjector : Injector<MainActivity> {
    override fun inject(target: MainActivity) {
        (target.applicationContext as KNABofonApp).component.plus(MainModule(target)).inject(target)
    }

}