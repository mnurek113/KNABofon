package com.code.knab.knabofon.utils.rx

import io.reactivex.Scheduler

interface RxUtils {
    val observScheduler: Scheduler
    val subscribeScheduler: Scheduler
}