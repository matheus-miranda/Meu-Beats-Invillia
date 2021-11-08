package com.invillia.meubeats

import android.app.Application
import com.invillia.meubeats.di.AppModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class BeatsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
        Timber.plant(Timber.DebugTree())
    }

    private fun startKoin() {
        startKoin {
            androidContext(this@BeatsApplication)
            modules(AppModules.modules)
        }
    }
}