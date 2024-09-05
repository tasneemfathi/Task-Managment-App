package com.example.taskmanagmentapp

import android.content.Context
import androidx.room.Room
import com.example.taskmanagmentapp.model.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule{

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext:Context):Database{
        return  Room.databaseBuilder(
            appContext,
            Database::class.java,
            "tasks_database"
        ).build()
    }


    @Provides
    @Singleton
    fun provideTasksDao(database:Database):TaskDao{
        return database.taskDao()
    }
}