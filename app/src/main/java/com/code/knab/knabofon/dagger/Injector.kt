package com.code.knab.knabofon.dagger

interface Injector<in T> {
    fun inject(target:T)
}