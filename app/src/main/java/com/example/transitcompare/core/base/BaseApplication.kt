package com.example.transitcompare.core.base

import android.app.Application
import com.example.transitcompare.core.modules.allModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(allModules)
        }
    }
}