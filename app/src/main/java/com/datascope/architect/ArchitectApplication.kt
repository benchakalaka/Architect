package com.datascope.architect

import android.app.Application
import com.datascope.architect.ioc.architectModule
import com.datascope.architect.ioc.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ArchitectApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Koin Android logger
            androidLogger(Level.ERROR)

            //inject Android context
            androidContext(this@ArchitectApplication)
            // use modules
            modules(architectModule, networkModule)
        }

    }

}