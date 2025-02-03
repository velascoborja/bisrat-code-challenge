package com.touch_surgery.digital_surgery.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.touch_surgery.digital_surgery.data.local.converters.PhaseTypeConverters
import com.touch_surgery.digital_surgery.data.local.converters.ProcedureTypeConverters
import com.touch_surgery.digital_surgery.data.local.dao.ProcedureDao
import com.touch_surgery.digital_surgery.data.local.dao.ProcedureDetailDao
import com.touch_surgery.digital_surgery.data.local.model.PhaseEntity
import com.touch_surgery.digital_surgery.data.local.model.ProcedureDetailEntity
import com.touch_surgery.digital_surgery.data.local.model.ProcedureEntity
import com.touch_surgery.digital_surgery.data.local.model.PhaseIconEntity
import com.touch_surgery.digital_surgery.data.local.model.CardEntity

@Database(
    entities = [ProcedureEntity::class, ProcedureDetailEntity::class,
        PhaseEntity::class, PhaseIconEntity::class, CardEntity::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(ProcedureTypeConverters::class,PhaseTypeConverters::class)
abstract class ProcedureDatabase : RoomDatabase() {
    abstract fun procedureDao(): ProcedureDao
    abstract fun procedureDetailDao(): ProcedureDetailDao
}