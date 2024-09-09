package com.farkasatesz.mytodoapp

import android.app.Application
import com.farkasatesz.mytodoapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TodoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TodoApplication)
            modules(appModule)
        }

        }
}