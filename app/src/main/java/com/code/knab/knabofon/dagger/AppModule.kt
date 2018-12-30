package com.code.knab.knabofon.dagger

import android.content.Context
import com.code.knab.knabofon.utils.rx.RxUtils
import com.code.knab.knabofon.utils.rx.RxUtilsImpl
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class AppModule(context: Context) {

    @Provides
    @Singleton
    fun provideRxUtils(): RxUtils = RxUtilsImpl(AndroidSchedulers.mainThread(),
            Schedulers.from(Executors.newFixedThreadPool(4)))

}