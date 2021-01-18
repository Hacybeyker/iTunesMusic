package com.hacybeyker.repository.local.database

import androidx.room.TypeConverter
import java.util.*

class TypeConverts {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}