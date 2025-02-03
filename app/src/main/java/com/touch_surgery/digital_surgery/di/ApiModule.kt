package com.touch_surgery.digital_surgery.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.touch_surgery.digital_surgery.data.remote.api.ProcedureApi
import com.touch_surgery.utils.Const
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.Date
import java.util.concurrent.TimeUnit


val apiModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofitClient(get()) }
    single { provideProcedureApi(get()) }
}


fun provideRetrofitClient(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Const.BASE_URL)
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi
                    .Builder()
                    .add(KotlinJsonAdapterFactory())
                    .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
                    .build()
            )
        )
        .client(okHttpClient).build()
}

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient().newBuilder().apply {
        connectTimeout(120, TimeUnit.SECONDS)
        readTimeout(120, TimeUnit.SECONDS)
        writeTimeout(120, TimeUnit.SECONDS)
        addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        dispatcher(Dispatcher().apply {
            maxRequests = 3
        })
    }.build()
}

fun provideProcedureApi(retrofit: Retrofit) : ProcedureApi = retrofit.create(ProcedureApi::class.java)