package com.code.knab.knabofon

import android.app.Application
import com.code.knab.knabofon.dagger.DaggerAppComponent

class KNABofonApp: Application() {

    val component by lazy {
        DaggerAppComponent.builder()
                .build()
    }
}