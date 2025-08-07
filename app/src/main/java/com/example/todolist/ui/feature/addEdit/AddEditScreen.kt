package com.example.todolist.ui.feature.addEdit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolist.ui.UiEvent
import com.example.todolist.ui.theme.TodoListTheme

@Composable
fun AddEditScreen(
    id: Long? = null,
    viewModel: AddEditViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
) {
    val title = viewModel.title
    val description = viewModel.description

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.NavigateBack -> navigateBack()
                is UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(event.message)
                }
                is UiEvent.Navigate<*> -> {}
            }
        }
    }

    AddEditContent(title, description, viewModel::onEvent, snackbarHostState)
}

    @Composable
    fun AddEditContent(
        title: String,
        description: String? = null,
        onEvent: (AddEditEvent) -> Unit,
        snackbarHostState: SnackbarHostState,
    ) {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    onEvent(AddEditEvent.Save)
                }) {
                    Icon(Icons.Default.Check, contentDescription = "Save")
                }
            },
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .consumeWindowInsets(paddingValues)
                    .padding(16.dp),
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = title,
                    onValueChange = {
                        onEvent(AddEditEvent.TitleChanged(it))
                    },
                    placeholder = {
                        Text("Title")
                    }
                )
                Spacer(modifier = Modifier.padding(16.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = description ?: "",
                    onValueChange = {
                        onEvent(AddEditEvent.DescriptionChanged(it))
                    },
                    placeholder = {
                        Text("Description (optional)")
                    }
                )
            }
        }
    }

    @Preview
    @Composable
    private fun AdEditContentPreview() {
        TodoListTheme {
            AddEditContent("Title", "Description", {}, SnackbarHostState())
        }
    }