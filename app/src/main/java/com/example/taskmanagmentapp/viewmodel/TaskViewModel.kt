package com.example.taskmanagmentapp.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanagmentapp.model.Task
import com.example.taskmanagmentapp.repo.TaskRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(val taskRepo:TaskRepo) : ViewModel(){
    private val _tasksUIState:MutableStateFlow<List<Task>> = MutableStateFlow(emptyList())
    val tasksUIState: StateFlow<List<Task>> = _tasksUIState.asStateFlow()

    private val _task = MutableStateFlow<Task?>(null)
    val task = _task.asStateFlow()


    fun getTasks(){
        viewModelScope.launch {
            taskRepo.getTasks().collect{
                _tasksUIState.value = it
            }
        }
    }

    fun getTask(id:Int){
        viewModelScope.launch {
            taskRepo.getTask(id = id).collect{
                _task.value = it
            }
        }
    }

    fun addTask(task:Task){
        viewModelScope.launch {
            taskRepo.addTask(task)
        }
    }

    fun updateTask(task:Task){
        viewModelScope.launch {
            taskRepo.updateTask(task)
        }
    }

    fun deleteTask(task:Task){
        viewModelScope.launch {
            taskRepo.deleteTask(task)
        }
    }
}