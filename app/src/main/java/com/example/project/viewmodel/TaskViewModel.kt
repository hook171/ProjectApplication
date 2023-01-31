package com.example.project.viewmodel

import android.app.Application
import android.app.DownloadManager.Query
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.model.Task
import com.example.project.repository.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(app: Application, private val taskRepository: TaskRepository): AndroidViewModel(app) {

    fun addTask(task: Task) = viewModelScope.launch {
        taskRepository.addTask(task)
    }

    fun updateTask(task: Task) = viewModelScope.launch {
        taskRepository.updateTask(task)
    }

    fun deleteTask(task: Task) = viewModelScope.launch {
        taskRepository.deleteTask(task)

    }

    fun getAllTask() = taskRepository.getAllTasks()

    fun searchTask(query: String?) = taskRepository.searchTask(query)


}