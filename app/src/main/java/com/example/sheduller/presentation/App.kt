package com.example.sheduller.presentation

import android.app.Application
import com.example.sheduller.presentation.di.moduleAuthentication
import com.example.sheduller.presentation.di.moduleCreateGroup
import com.example.sheduller.presentation.di.moduleDB
import com.example.sheduller.presentation.di.moduleEvents
import com.example.sheduller.presentation.di.moduleGroups
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Koin Android logger
            androidLogger()
            //inject Android context
            androidContext(this@App)

            modules(
                moduleDB, moduleEvents, moduleAuthentication, moduleCreateGroup, moduleGroups
            )

        }

    }


}