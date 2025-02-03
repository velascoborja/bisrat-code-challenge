package com.touch_surgery.digital_surgery.data.local.converters

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.touch_surgery.digital_surgery.data.local.model.CardEntity
import com.touch_surgery.digital_surgery.data.local.model.PhaseEntity
import com.touch_surgery.digital_surgery.data.local.model.PhaseIconEntity

class PhaseTypeConverters {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()


    @TypeConverter
    fun fromPhaseList(phases: List<PhaseEntity>?): String? {
        if (phases == null) return null
        val type = Types.newParameterizedType(List::class.java, PhaseEntity::class.java)
        val adapter = moshi.adapter<List<PhaseEntity>>(type)
        return adapter.toJson(phases)
    }

    @TypeConverter
    fun toPhaseList(json: String?): List<PhaseEntity>? {
        if (json == null) return null
        val type = Types.newParameterizedType(List::class.java, PhaseEntity::class.java)
        val adapter = moshi.adapter<List<PhaseEntity>>(type)
        return adapter.fromJson(json)
    }

    @TypeConverter
    fun fromCard(card: CardEntity?): String? {
        if (card == null) return null
        val adapter = moshi.adapter(CardEntity::class.java)
        return adapter.toJson(card)
    }

    @TypeConverter
    fun toCard(json: String?): CardEntity? {
        if (json == null) return null
        val adapter = moshi.adapter(CardEntity::class.java)
        return adapter.fromJson(json)
    }

    // PhaseEntity converters
    @TypeConverter
    fun fromPhaseIcon(icon: PhaseIconEntity?): String? {
        if (icon == null) return null
        val adapter = moshi.adapter(PhaseIconEntity::class.java)
        return adapter.toJson(icon)
    }

    @TypeConverter
    fun toPhaseIcon(json: String?): PhaseIconEntity? {
        if (json == null) return null
        val adapter = moshi.adapter(PhaseIconEntity::class.java)
        return adapter.fromJson(json)
    }
}