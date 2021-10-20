package com.example.cartrack.util

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.pm.ApplicationInfo
import android.graphics.Point
import android.os.Build
import android.provider.Settings
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.FloatRange

/**
 * A helper class for device
 * @author quoi.tran@com.zyrous.com
 * @date 05.15.2019
 */

object DeviceUtils {
    val deviceName: String
        get() {
            val manufacturer = Build.MANUFACTURER
            val model = Build.MODEL
            return if (model.startsWith(manufacturer)) {
                StringUtils.capitalize(model)
            } else {
                StringUtils.capitalize(manufacturer) + " " + model
            }
        }

    val osVersion: String
        get() = Build.VERSION.RELEASE

    /**
     * Get device Id
     * @param context Context
     * @return device id as String
     * @suppress HardwareIds
     */
    @SuppressLint("HardwareIds")
    fun getDeviceId(context: Context): String {
        return Settings.Secure.getString(context.contentResolver,
                Settings.Secure.ANDROID_ID)
    }

    /**
     * Calculate cache size
     * @param context Context
     * @param size @FloatRange(from = 0.01, to = 1.0)
     * @return cache size as Long
     */
    fun calcCacheSize(context: Context, @FloatRange(from = 0.01, to = 1.0) size: Float): Long {
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as? ActivityManager
        val largeHeap = context.applicationInfo.flags or ApplicationInfo.FLAG_LARGE_HEAP != 0
        val memoryClass = (if (largeHeap) am?.largeMemoryClass else am?.memoryClass)?.toLong()
        return (memoryClass?.toFloat() ?: 0 * 1024f * 1024f * size).toLong()
    }

    /**
     * Get device screen width
     * @param context Context
     * @return device screen width
     */
    fun getDeviceScreenWidth(context: Context): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as? WindowManager
        val display = wm?.defaultDisplay
        val size = Point()
        display?.getSize(size)
        return size.x
    }

    /**
     * Get device screen height
     * @param context Context
     * @return device screen height
     */
    fun getDeviceScreenHeight(context: Context): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as? WindowManager
        val display = wm?.defaultDisplay
        val size = Point()
        display?.getSize(size)
        return size.y
    }
}