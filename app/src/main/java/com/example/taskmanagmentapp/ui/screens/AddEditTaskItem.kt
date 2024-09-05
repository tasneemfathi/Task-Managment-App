package com.example.taskmanagmentapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taskmanagmentapp.model.Task
import com.example.taskmanagmentapp.ui.components.DeadlineSelector
import com.example.taskmanagmentapp.ui.components.PrioritySelector
import com.example.taskmanagmentapp.viewmodel.TaskViewModel
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTaskItem(
    viewModel: TaskViewModel,
    taskId: Int?,
    onTaskSaved: (Task) -> Unit,
    onCanceled: () -> Unit) {
    val task by viewModel.task.collectAsState(initial = null)
    LaunchedEffect(taskId) {
        if(taskId != null)
        viewModel.getTask(taskId)
    }

    task.let {
        AddEditTaskItemContent(
            task = it,
            onTaskSaved = {
                onTaskSaved(it)
            },
            onCanceled = {
                onCanceled()
            }
        )
    }

}


@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun AddEditTaskItemContent(
    task: Task?,
    onTaskSaved: (Task) -> Unit,
    onCanceled: () -> Unit,
) {
    var title by remember(task) { mutableStateOf(task?.title ?: "") }
    var description by remember(task) { mutableStateOf(task?.description ?: "") }
    var priority by remember(task) { mutableIntStateOf(task?.priority ?: 0) }
    var deadline by remember(task) { mutableLongStateOf(task?.deadline ?: System.currentTimeMillis()) }

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Title : ${task?.title ?: "New Task"}") },
            actions = {
                IconButton(onClick = {
                    onTaskSaved(
                        Task(
                            id = task?.id?:0,
                            title = title,
                            description = description,
                            priority = priority,
                            deadline = deadline,
                            isCompleted = task?.isCompleted ?: false
                        )
                    )
                }) {
                    Icon(Icons.Default.Check, contentDescription = "Save Task")
                }

                IconButton(onClick = { onCanceled() }) {
                    Icon(Icons.Default.Clear, contentDescription = "Cancel Task")
                }
            })
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = it.calculateTopPadding(), horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.Start
        ) {

            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(text = "Title") },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text(text = "Description") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = false
            )

            PrioritySelector(priority = priority) {
                priority = it
            }

            DeadlineSelector(deadline = deadline) {
                deadline = it
            }

        }
    }
}