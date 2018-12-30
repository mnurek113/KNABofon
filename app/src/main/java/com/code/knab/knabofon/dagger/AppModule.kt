package com.code.knab.knabofon.dagger

import com.code.knab.knabofon.utils.RxUtils
import com.code.knab.knabofon.utils.RxUtilsImpl
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideRxUtils(): RxUtils = RxUtilsImpl(AndroidSchedulers.mainThread(),
            Schedulers.from(Executors.newFixedThreadPool(4)))

}