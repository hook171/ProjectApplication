package com.example.project.repository

import androidx.lifecycle.LiveData
import com.example.project.db.TaskDatabase
import com.example.project.model.Task

class TaskRepository(private val db: TaskDatabase) {

    suspend fun addTask(task: Task) = db.getTaskDao().addTask(task)

    suspend fun updateTask(task: Task) = db.getTaskDao().updateTask(task)

    suspend fun deleteTask(task: Task) = db.getTaskDao().deleteTask(task)

    fun getAllTasks() = db.getTaskDao().getAllTasks()

    fun searchTask(query: String?) = db.getTaskDao().searchTask(query)

}