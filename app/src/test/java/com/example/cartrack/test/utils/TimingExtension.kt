package com.example.cartrack.test.utils

import org.junit.jupiter.api.extension.AfterTestExecutionCallback
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ExtensionContext.Store
import java.util.logging.Logger

/**
 * @author n.quoi
 * @date 10.27.2021
 */

class TimingExtension : BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private val logger = Logger.getLogger(TimingExtension::class.java.name)

    private val START_TIME = "start time"

    override fun beforeTestExecution(context: ExtensionContext?) {
        context?.let { getStore(it).put(START_TIME, System.currentTimeMillis()) }
    }

    override fun afterTestExecution(context: ExtensionContext?) {
        context?.let {
            val testMethod = it.requiredTestMethod
            val startTime = getStore(it).remove(START_TIME, Long::class.javaPrimitiveType)
            val duration = System.currentTimeMillis() - startTime

            logger.info { String.format("Method [%s] took %s ms.", testMethod.name, duration) }
        }
    }

    private fun getStore(context: ExtensionContext): Store {
        return context.getStore(
            ExtensionContext.Namespace.create(
                javaClass,
                context.requiredTestMethod
            )
        )
    }
}