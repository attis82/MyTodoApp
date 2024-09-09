package com.farkasatesz.mytodoapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.farkasatesz.mytodoapp.models.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("select * from todos where is_completed = 0 order by deadline")
    fun getActiveTodos() : Flow<List<Todo>>
    @Query("select * from todos where is_completed = 1")
    fun getCompletedTodos() : Flow<List<Todo>>
    @Upsert
    suspend fun upsertTodo(todo: Todo)
    @Delete
    suspend fun deleteTodo(todo: Todo)
    @Query("delete from todos where is_completed = 0")
    suspend fun deleteAllActiveTodos()
    @Query("delete from todos where is_completed = 1")
    suspend fun deleteAllCompletedTodos()

}