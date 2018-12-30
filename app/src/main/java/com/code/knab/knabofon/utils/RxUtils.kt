package com.code.knab.knabofon.utils

import io.reactivex.Scheduler

interface RxUtils {
    val observScheduler: Scheduler
    val subscribeScheduler: Scheduler
}