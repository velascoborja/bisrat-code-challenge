package com.touch_surgery.digital_surgery.data.local.converters

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types


class ProcedureTypeConverters {

    private val moshi = Moshi.Builder().build()

    @TypeConverter
    fun listToJson(list: List<String>): String =
        moshi.adapter<List<String>>(
            Types.newParameterizedType(
                List::class.java,
                String::class.java
            )
        ).toJson(list)

    @TypeConverter
    fun jsonToList(json: String): List<String> =
        moshi.adapter<List<String>>(
            Types.newParameterizedType(
                List::class.java,
                String::class.java
            )
        ).fromJson(json) ?: emptyList()
}