package com.farkasatesz.mytodoapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity(tableName = "todos")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "todo_id")
    val todoId: Long? = null,

    @ColumnInfo(name = "todo_title")
    val todoTitle: String,

    @ColumnInfo(name = "todo_description")
    val todoDescription: String,

    @ColumnInfo(name = "is_completed")
    val isCompleted: Boolean,

    @ColumnInfo(name = "deadline")
    val deadLine: Date,

    @ColumnInfo(name = "created_at")
    val createdAt: Date = Date()
)