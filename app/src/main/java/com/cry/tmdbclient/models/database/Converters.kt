package com.cry.tmdbclient.models.database

import androidx.room.TypeConverter
import org.threeten.bp.Instant

class Converters
{
    companion object {
        @TypeConverter
        @JvmStatic
        fun fromBoolean(value: Boolean): Int {
            return if (value) DatabaseContract.INT_TRUE else DatabaseContract.INT_FALSE
        }

        @TypeConverter
        @JvmStatic
        fun toBoolean(value: Int): Boolean {
            return value == DatabaseContract.INT_TRUE
        }

        @TypeConverter
        @JvmStatic
        fun fromInstant(value: Instant?): Long? {
            return value?.toEpochMilli()
        }

        @TypeConverter
        @JvmStatic
        fun toInstant(value: Long?): Instant? {
            return value?.let { Instant.ofEpochMilli(it) }
        }
    }
}