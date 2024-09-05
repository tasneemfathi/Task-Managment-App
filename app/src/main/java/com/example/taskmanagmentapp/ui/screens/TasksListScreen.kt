package com.example.taskmanagmentapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import com.example.taskmanagmentapp.model.Task
import com.example.taskmanagmentapp.ui.components.TaskItem
import com.example.taskmanagmentapp.viewmodel.TaskViewModel

@Composable
fun TasksListScreen(
    viewModel: TaskViewModel,
    onAddAction: () -> Unit,
    onEdit: (Task) -> Unit
){
    val tasks by viewModel.tasksUIState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getTasks()
    }
    TasksListScreenContent(
        tasks,
        onAddAction,
        onTaskEdit = {onEdit(it)},
        onTaskDeleted = { viewModel.deleteTask(it) },
        onTaskCheckedChange = { viewModel.updateTask(it) }
    )
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TasksListScreenContent(
    tasks: List<Task>,
    onAddAction: () -> Unit,
    onTaskEdit: (Task) -> Unit,
    onTaskDeleted: (Task) -> Unit,
    onTaskCheckedChange: (Task) -> Unit){
    Scaffold(topBar =
    {
        TopAppBar(title = { 
            Text(text = "Task Management") } ,
            actions = {
                IconButton(onClick =  onAddAction){
                    Icon(Icons.Default.Add, contentDescription = "Add New Task")
                }
            })
    }){

        LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = it.calculateTopPadding()),
            verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(tasks){ task: Task ->
                // implement task item
                TaskItem(
                    task = task,
                    onTaskCheckedChange = { onTaskCheckedChange(task.copy(isCompleted = it)) },
                    onTaskEdited = { onTaskEdit(task) },
                    onTaskDeleted = { onTaskDeleted(task) }
                )
            }

        }
    }
}