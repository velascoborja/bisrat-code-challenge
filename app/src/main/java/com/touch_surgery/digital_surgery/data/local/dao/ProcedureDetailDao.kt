package com.touch_surgery.digital_surgery.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.touch_surgery.digital_surgery.data.local.model.ProcedureDetailEntity
import com.touch_surgery.digital_surgery.data.local.model.ProcedureEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProcedureDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetails(procedureDetail: ProcedureDetailEntity)

    @Query("SELECT * FROM ProcedureDetailEntity WHERE uuid = :procedureID")
    fun getProcedureDetail(procedureID: String) : Flow<ProcedureDetailEntity?>

    @Query("SELECT * FROM ProcedureEntity WHERE uuid = :procedureID")
    fun getSingleProcedure(procedureID: String) : Flow<ProcedureEntity>
}