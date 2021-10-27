package com.example.cartrack.test.utils

import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module

/**
 * @author n.quoi
 * @date 05.28.2019
 */

class KoinExtension(private vararg val modules: Module = emptyArray()) : BeforeEachCallback,
    AfterEachCallback {

    override fun beforeEach(context: ExtensionContext?) {
        /**
         * Start Koin
         */
        startKoin {
            modules(*modules)
        }
    }

    override fun afterEach(context: ExtensionContext?) = stopKoin()
}