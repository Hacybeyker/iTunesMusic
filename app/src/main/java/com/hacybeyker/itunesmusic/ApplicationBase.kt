package com.hacybeyker.itunesmusic

import android.app.Application
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ApplicationBase : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@ApplicationBase)
            koin.loadModules(arrayListOf())
            koin.createRootScope()
        }
        getKoin().run {
            setProperty("BASE_URL", BuildConfig.BASE_URL)
        }
    }
}