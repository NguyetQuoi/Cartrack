package com.example.cartrack.di

import com.example.cartrack.test.utils.TestSchedulerProvider
import com.example.cartrack.util.rx.SchedulerProvider
import org.koin.dsl.module

/**
 * Test rx module
 * @author n.quoi
 * @date 05.25.2019
 */

val testRxModule = module {
    // provided components
    single<SchedulerProvider> { TestSchedulerProvider() }
}