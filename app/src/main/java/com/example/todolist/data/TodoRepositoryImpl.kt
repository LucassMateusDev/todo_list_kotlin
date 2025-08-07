package com.example.todolist.data

import com.example.todolist.domain.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TodoRepositoryImpl(
    private val dao: TodoDao,
) : TodoRepository {
    override suspend fun insert(todo: Todo) {
        val entity = todo.toTodoEntity()
        dao.insert(entity)
    }

    override suspend fun updateCompleted(id: Long, isCompleted: Boolean) {
        val entity = dao.getById(id) ?: return
        val updatedEntity = entity.copy(isCompleted = isCompleted)
        dao.insert(updatedEntity)
    }

    override suspend fun delete(todo: Todo) {
        val entity = dao.getById(todo.id) ?: return
        dao.delete(entity)
    }

    override fun getAll(): Flow<List<Todo>> {
        return dao.getAll().map { entities ->
            entities.map { entity ->
                entity.toTodo()
            }
        }
    }

    override suspend fun getById(id: Long): Todo? {
        return dao.getById(id)?.toTodo()
    }

    private fun TodoEntity.toTodo(): Todo {
        return Todo(
            id = id,
            title = title,
            description = description,
            isCompleted = isCompleted
        )
    }

    private fun Todo.toTodoEntity(): TodoEntity {
        return TodoEntity(
            id = id,
            title = title,
            description = description,
            isCompleted = isCompleted
        )
    }
}