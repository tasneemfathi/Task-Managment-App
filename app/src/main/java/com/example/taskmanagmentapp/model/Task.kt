package com.example.taskmanagmentapp.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val priority: Int,
    val deadline: Long,
    val isCompleted: Boolean,
)


@Dao
interface TaskDao {
    @Query("Select * From task Order By priority Desc")
    fun getTasks(): Flow<List<Task>>

    @Query("Select * From task Where id = :id")
    fun getTask(id:Int): Flow<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)
}