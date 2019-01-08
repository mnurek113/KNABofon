package com.code.knab.knabofon.ui.transmition.dagger

import com.code.knab.knabofon.ui.transmition.TransmitionActivity
import dagger.Subcomponent

@TransmitionScope
@Subcomponent(modules = arrayOf(TransmitionModule::class))
interface TransmitionComponent {

    fun inject(activity: TransmitionActivity)

}