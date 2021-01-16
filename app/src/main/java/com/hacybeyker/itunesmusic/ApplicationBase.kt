package com.hacybeyker.itunesmusic

import android.app.Application
import com.hacybeyker.repository.network.di.BASE_URL
import com.hacybeyker.repository.network.di.networkModule
import com.hacybeyker.repository.network.di.retrofitModule
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
            koin.loadModules(arrayListOf(
                retrofitModule,
                networkModule
            ))
            koin.createRootScope()
        }
        getKoin().run {
            setProperty(BASE_URL, BuildConfig.BASE_URL)
        }
    }
}