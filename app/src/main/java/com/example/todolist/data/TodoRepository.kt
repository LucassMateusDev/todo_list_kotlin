package com.example.todolist.data

import com.example.todolist.domain.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    suspend fun insert(todo: Todo)
    suspend fun updateCompleted(id: Long, isCompleted: Boolean)
    suspend fun delete(todo: Todo)
    fun getAll(): Flow<List<Todo>>
    suspend fun getById(id: Long): Todo?
}