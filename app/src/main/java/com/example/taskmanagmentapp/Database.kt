package com.example.taskmanagmentapp

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskmanagmentapp.model.Task
import com.example.taskmanagmentapp.model.TaskDao


@Database(entities = [Task::class], version = 2)
abstract class Database : RoomDatabase(){
    abstract fun taskDao(): TaskDao
}