package com.farkasatesz.mytodoapp.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farkasatesz.mytodoapp.models.Todo
import com.farkasatesz.mytodoapp.repositories.TodoRepository
import com.farkasatesz.mytodoapp.state.ListType
import com.farkasatesz.mytodoapp.state.TodoState
import com.farkasatesz.mytodoapp.utils.Converter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class TodoViewModel(private val repository: TodoRepository) : ViewModel() {

    private val _state = MutableStateFlow(TodoState())
    val state = _state.asStateFlow()

    private val _listType = MutableStateFlow(ListType.ACTIVE)
    val list = _listType.flatMapLatest { type ->
        when(type){
            ListType.ACTIVE -> {repository.getActiveTodos()}
            ListType.COMPLETED -> {repository.getCompletedTodos()}
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun changeListType(type: ListType){
        _listType.value = type
    }

    fun setTitle(title: String){
        _state.value = _state.value.setTitle(title)
    }

    fun setDescription(description: String){
        _state.value = _state.value.setDescription(description)
    }

    fun setDeadline(deadline: String){
        val convertedDeadline = Converter.convertStringToDate(deadline)
        _state.value = _state.value.setDeadLine(convertedDeadline)
    }

    fun upsertTodo(todo: Todo?){
        if (!validation()) return
        viewModelScope.launch {
            val newTodo = Todo(
                todoId = todo?.todoId,
                todoTitle = todo?.todoTitle ?: _state.value.todoTitle,
                todoDescription = todo?.todoDescription ?: _state.value.todoDescription,
                isCompleted = todo?.isCompleted ?: _state.value.isCompleted,
                deadLine = todo?.deadLine ?: _state.value.deadLine
            )
            try {
                repository.upsertTodo(newTodo)
            }catch (e: Exception){
                Log.e("upsertTodo", "upsertTodo: ${e.message.toString()}" )
            }
        }

    }

    fun deleteTodo(todo: Todo){
        viewModelScope.launch {
            try {
                repository.deleteTodo(todo)
            }catch (e: Exception){
                Log.e("deleteTodo", "deleteTodo: ${e.message.toString()}" )
            }
        }
    }

    fun deleteAllActiveTodos(){
        viewModelScope.launch {
            try {
                repository.deleteAllActiveTodos()
            } catch (e: Exception) {
                Log.e("deleteAllActiveTodos", "deleteAllActiveTodos: ${e.message.toString()}")
            }
        }
    }

    fun deleteAllCompletedTodos(){
        viewModelScope.launch {
            try {
                repository.deleteAllCompletedTodos()
            } catch (e: Exception) {
                Log.e("deleteAllCompletedTodos", "deleteAllCompletedTodos: ${e.message.toString()}")
            }
        }
    }

    fun setCompletion(isCompleted: Boolean){
        _state.value = _state.value.setCompleted(isCompleted)
    }

    fun resetState(){
        _state.value = _state.value.reset()
    }

    fun showDialog(){
        _state.value = _state.value.showDialog()
    }

    fun hideDialog(){
        _state.value = _state.value.hideDialog()
    }

    fun validation() : Boolean{
        return _state.value.todoTitle.isNotBlank() && _state.value.todoDescription.isNotBlank()
    }






}