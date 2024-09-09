package com.farkasatesz.mytodoapp.converters

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {

    @TypeConverter
    fun convertFromTimestamp(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun convertToTimestamp(date: Date): Long {
        return date.time
    }
}