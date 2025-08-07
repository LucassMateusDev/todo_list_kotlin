package com.example.todolist.ui.feature.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.TodoRepository
import com.example.todolist.domain.Todo
import com.example.todolist.navigation.AddEditRoute
import com.example.todolist.ui.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor (
    private val repository: TodoRepository,
) : ViewModel() {

    val todos = repository.getAll().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: ListEvent) {
        when (event) {
            is ListEvent.DeleteTodo -> {
                deleteTodo(event.todo)
            }
            is ListEvent.CompleteTodoChanged -> {
                completeTodoChanged(event.todo)
            }
            is ListEvent.AddEditTodo -> {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.Navigate(AddEditRoute(event.id)))
                }
            }
        }
    }

    private fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            repository.delete(todo)
            _uiEvent.send(UiEvent.ShowSnackbar("Todo deleted"))
        }
    }

    private fun completeTodoChanged(todo: Todo) {
        viewModelScope.launch {
            repository.updateCompleted(todo.id, !todo.isCompleted)
            _uiEvent.send(UiEvent.ShowSnackbar("Todo updated"))
        }
    }

}