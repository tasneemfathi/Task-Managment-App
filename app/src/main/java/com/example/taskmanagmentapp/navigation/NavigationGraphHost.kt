package com.example.taskmanagmentapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.taskmanagmentapp.ui.screens.AddEditTaskItem
import com.example.taskmanagmentapp.ui.screens.TasksListScreen
import com.example.taskmanagmentapp.viewmodel.TaskViewModel


@Composable
fun NavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = AppDestinations.TasksListScreen.name) {
        composable(AppDestinations.TasksListScreen.name) {

            val viewModel = hiltViewModel<TaskViewModel>()
            TasksListScreen(
                viewModel = viewModel,
                onAddAction = { navController.navigate("${AppDestinations.AddEditTasksScreen.name}/-1") },
                onEdit = {
                    navController.navigate("${AppDestinations.AddEditTasksScreen.name}/${it.id}")
                }
            )
        }


        composable("${AppDestinations.AddEditTasksScreen.name}/{taskId}") {
            val viewModel = hiltViewModel<TaskViewModel>()
            val id = it.arguments?.getString("taskId")?.toInt() ?: 0
            AddEditTaskItem(
                viewModel = viewModel,
                taskId = id,
                onTaskSaved = {
                    if (id > 0 ) {
                        viewModel.updateTask(task = it)
                    } else {
                        viewModel.addTask(task = it)
                    }
                    navController.navigateUp()
                },
                onCanceled = { navController.navigateUp() }
            )

        }
    }
}