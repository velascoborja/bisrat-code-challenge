package com.touch_surgery.digital_surgery

import android.app.Application
import com.touch_surgery.digital_surgery.di.testViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DigitalSurgeryAppTest : Application() {

    override fun onCreate() {
        super.onCreate()

        /**
         * Start Koin dependency
         */
        startKoin {
            androidContext(this@DigitalSurgeryAppTest)
            modules(testViewModelModule)
        }
    }
}