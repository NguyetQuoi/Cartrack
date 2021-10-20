package com.example.cartrack

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.multidex.BuildConfig
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.example.cartrack.data.pref.PreferenceStorage
import com.example.cartrack.di.appModule
import com.example.cartrack.di.networkModule
import com.example.cartrack.di.repositoryModule
import com.example.cartrack.di.viewModelModule
import com.example.cartrack.util.ReleaseTree
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

/**
 * An Application for MyAssignment
 * @author n.quoi
 * @date 05.07.2019
 */

class AssignmentApplication : MultiDexApplication(), Application.ActivityLifecycleCallbacks {

    private val preferenceStorage: PreferenceStorage by inject()

    private var activityReferences = 0
    private var isActivityChangingConfigurations = false

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(this)
        AndroidThreeTen.init(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(ReleaseTree())
        }

        initKoin()

    }

    private fun initKoin(){
        startKoin {
            androidLogger()
            androidContext(this@AssignmentApplication)
            // declare modules
            modules(
                networkModule,
                appModule,
                repositoryModule,
                viewModelModule
            )
        }
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        //TODO: implement later
    }

    override fun onActivityStarted(activity: Activity) {
        if (++activityReferences == 1 && !isActivityChangingConfigurations) {
            Timber.d("App is foreground")
            preferenceStorage.appIsForeground = true
        }
    }

    override fun onActivityResumed(activity: Activity) {
        //TODO: implement later
    }

    override fun onActivityPaused(activity: Activity) {
        //TODO: implement later
    }

    override fun onActivityStopped(activity: Activity) {
        isActivityChangingConfigurations = activity.isChangingConfigurations
        if (--activityReferences == 0 && !isActivityChangingConfigurations) {
            Timber.d("App is background")
            preferenceStorage.appIsForeground = false
        }
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        //TODO: implement later
    }

    override fun onActivityDestroyed(activity: Activity) {
        //TODO: implement later
    }
}