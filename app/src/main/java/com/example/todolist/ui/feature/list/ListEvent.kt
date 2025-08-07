package com.example.todolist.ui.feature.list

import com.example.todolist.domain.Todo

sealed interface ListEvent {
    data class CompleteTodoChanged(val todo: Todo) : ListEvent
    data class DeleteTodo(val todo: Todo) : ListEvent
    data class AddEditTodo(val id: Long? = null) : ListEvent
}