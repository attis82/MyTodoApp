package com.farkasatesz.mytodoapp.state

import java.util.Date

data class TodoState(
    val todoTitle: String = "",
    val todoDescription: String = "",
    val isCompleted: Boolean = false,
    val deadLine: Date = Date(),
    val createdAt: Date = Date(),
    val isDialogPresent: Boolean = false
){
    fun setTitle(title: String) = copy(todoTitle = title)
    fun setDescription(description: String) = copy(todoDescription = description)
    fun setCompleted(isCompleted: Boolean) = copy(isCompleted = isCompleted)
    fun setDeadLine(deadLine: Date) = copy(deadLine = deadLine)
    fun showDialog() = copy(isDialogPresent = true)
    fun hideDialog() = copy(isDialogPresent = false)
    fun reset() = copy(
        todoTitle = "",
        todoDescription = "",
        isCompleted = false,
        deadLine = Date(),
    )
}
