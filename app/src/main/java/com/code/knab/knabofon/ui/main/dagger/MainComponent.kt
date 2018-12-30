package com.code.knab.knabofon.ui.main.dagger

import com.code.knab.knabofon.ui.main.MainActivity
import dagger.Subcomponent

@MainScope
@Subcomponent(modules = arrayOf((MainModule::class)))
interface MainComponent {
    fun inject(activity: MainActivity)
}