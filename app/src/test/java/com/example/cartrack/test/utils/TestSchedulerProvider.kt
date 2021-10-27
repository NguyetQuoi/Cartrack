package com.example.cartrack.test.utils

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import com.example.cartrack.util.rx.SchedulerProvider

/**
 * SchedulerProvider for Test
 * @author n.quoi
 * @date 05.23.2019
 */

class TestSchedulerProvider : SchedulerProvider {
    override fun io(): Scheduler = Schedulers.trampoline()

    override fun ui(): Scheduler = Schedulers.trampoline()

    override fun computation(): Scheduler = Schedulers.trampoline()
}