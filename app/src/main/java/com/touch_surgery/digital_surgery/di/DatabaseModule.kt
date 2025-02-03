package com.touch_surgery.digital_surgery.di

import android.app.Application
import androidx.room.Room
import com.touch_surgery.digital_surgery.data.local.ProcedureDatabase
import com.touch_surgery.digital_surgery.data.local.dao.ProcedureDao
import com.touch_surgery.digital_surgery.data.local.dao.ProcedureDetailDao
import com.touch_surgery.utils.Const
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val procedureDataBaseModule = module {

    fun provideDatabase(application: Application) : ProcedureDatabase {
        return Room.databaseBuilder(
            application, ProcedureDatabase::class.java, Const.DATABASE_NAME)
            .fallbackToDestructiveMigrationFrom()
            .build()
    }

    fun provideProcedureDao(database: ProcedureDatabase) : ProcedureDao = database.procedureDao()
    fun provideProcedureDetailDao(database : ProcedureDatabase) : ProcedureDetailDao = database.procedureDetailDao()

    single { provideDatabase(androidApplication()) }
    single { provideProcedureDao(get()) }
    single { provideProcedureDetailDao(get()) }
}