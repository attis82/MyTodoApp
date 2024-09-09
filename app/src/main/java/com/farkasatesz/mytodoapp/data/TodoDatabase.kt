package com.farkasatesz.mytodoapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.farkasatesz.mytodoapp.converters.DateConverter
import com.farkasatesz.mytodoapp.models.Todo

@Database(entities = [Todo::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}