package com.code.knab.knabofon.dagger

import com.code.knab.knabofon.dagger.AppModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class))
@Singleton
interface AppComponent {
}