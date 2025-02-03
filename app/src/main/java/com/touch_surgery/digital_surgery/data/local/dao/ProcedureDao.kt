package com.touch_surgery.digital_surgery.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.touch_surgery.digital_surgery.data.local.model.ProcedureEntity
import com.touch_surgery.utils.Const
import kotlinx.coroutines.flow.Flow

@Dao
interface ProcedureDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertProcedures(procedures: List<ProcedureEntity>)

    @Query(Const.GET_ALL_PROCEDURES)
    fun getAllProcedures(): Flow<List<ProcedureEntity>>


    @Query(Const.UPDATE_FAVOURITE_PROCEDURE)
     suspend fun updateFavorite(uuid: String, isFavourite: Boolean)

}