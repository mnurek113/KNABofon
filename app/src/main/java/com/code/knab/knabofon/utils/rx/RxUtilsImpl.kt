package com.code.knab.knabofon.utils.rx

import io.reactivex.Scheduler

class RxUtilsImpl(override val observScheduler: Scheduler,
                  override val subscribeScheduler: Scheduler) : RxUtils {
}