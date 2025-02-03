package com.touch_surgery.digital_surgery.presentation

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import com.touch_surgery.digital_surgery.R
import com.touch_surgery.digital_surgery.di.apiModule
import com.touch_surgery.digital_surgery.di.procedureDataBaseModule
import com.touch_surgery.digital_surgery.di.procedureDetailDomainModule
import com.touch_surgery.digital_surgery.di.procedureDetailRepositoryModule
import com.touch_surgery.digital_surgery.di.procedureDomainModule
import com.touch_surgery.digital_surgery.di.procedureRepositoryModule
import com.touch_surgery.digital_surgery.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class DigitalSurgeryApp : Application() , ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()

        /**
         * Start Koin dependency
         */
      startKoin {
          androidContext(this@DigitalSurgeryApp)
          androidLogger(Level.ERROR)
          modules(listOf(apiModule,viewModelModule, procedureDomainModule,
              procedureDetailDomainModule,
              procedureDataBaseModule,
              procedureRepositoryModule,
              procedureDetailRepositoryModule))
      }
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .crossfade(true)
            .placeholder(R.drawable.deafult_loading)
            .error(R.drawable.default_error)
            .fallback(R.drawable.no_image_available)
            .memoryCache {
                MemoryCache.Builder(this)
                    .maxSizePercent(0.20)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(this.cacheDir.resolve("coil_cache"))
                    .maxSizeBytes(5 * 1024 * 1024)
                    .build() }
            .respectCacheHeaders(false)
            .build()
    }
}