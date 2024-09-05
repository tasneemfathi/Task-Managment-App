package com.example.taskmanagmentapp.repo

import com.example.taskmanagmentapp.model.Task
import com.example.taskmanagmentapp.model.TaskDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepo @Inject constructor(private val tasksDao: TaskDao) {

    fun getTasks(): Flow<List<Task>> = tasksDao.getTasks()

    fun getTask(id:Int): Flow<Task> = tasksDao.getTask(id = id)

    suspend fun addTask(task:Task) = tasksDao.insertTask(task)

    suspend fun updateTask(task: Task) = tasksDao.updateTask(task)

    suspend fun deleteTask(task: Task) = tasksDao.deleteTask(task)

}