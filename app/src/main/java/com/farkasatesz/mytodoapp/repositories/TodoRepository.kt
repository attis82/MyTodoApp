package com.farkasatesz.mytodoapp.repositories

import com.farkasatesz.mytodoapp.data.TodoDao
import com.farkasatesz.mytodoapp.models.Todo
import kotlinx.coroutines.flow.Flow

class TodoRepository(private val dao : TodoDao) {
    fun getActiveTodos(): Flow<List<Todo>> = dao.getActiveTodos()
    fun getCompletedTodos(): Flow<List<Todo>> = dao.getCompletedTodos()
    suspend fun upsertTodo(todo: Todo) = dao.upsertTodo(todo)
    suspend fun deleteTodo(todo: Todo) = dao.deleteTodo(todo)
    suspend fun deleteAllActiveTodos() = dao.deleteAllActiveTodos()
    suspend fun deleteAllCompletedTodos() = dao.deleteAllCompletedTodos()
}