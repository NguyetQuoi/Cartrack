package com.example.cartrack.test.utils

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext

/**
 * Extension for InstantTaskExecutor
 * Support for testing
 *
 * @author n.quoi
 * @date 06.10.2019
 */

class InstantTaskExecutorExtension : BeforeAllCallback, AfterAllCallback {
    override fun beforeAll(context: ExtensionContext?) {
        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) {
                runnable.run()
            }

            override fun postToMainThread(runnable: Runnable) {
                runnable.run()
            }

            override fun isMainThread(): Boolean {
                return true
            }
        })
    }

    override fun afterAll(context: ExtensionContext?) {
        ArchTaskExecutor.getInstance().setDelegate(null)
    }

}