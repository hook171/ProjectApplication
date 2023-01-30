package com.example.project.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.project.repository.TaskRepository

class TaskViewModelProvider(val app: Application, private val taskRepository: TaskRepository): ViewModelProvider.Factory {


    override fun <T : ViewModel> create (modelclass: Class<T>): T {
        return TaskViewModel(app, taskRepository) as T
    }

}