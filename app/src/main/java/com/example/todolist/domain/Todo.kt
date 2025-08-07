package com.example.todolist.domain

data class Todo(
    val id: Long,
    val title: String,
    val description: String?,
    val isCompleted: Boolean,

)

// fake objects
val todo1 = Todo(1, "Title", "Description", true);
val todo2 = Todo(2, "Title2", "Description2", false);