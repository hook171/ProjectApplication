package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.project.databinding.ActivityMainBinding
import com.example.project.db.TaskDatabase
import com.example.project.repository.TaskRepository
import com.example.project.viewmodel.TaskViewModel
import com.example.project.viewmodel.TaskViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.toolbar)

        setUpViewModel()
    }

    private fun setUpViewModel(){

        val taskRepository = TaskRepository(
            TaskDatabase(this)
        )

        val viewModelProviderFactory = TaskViewModelProvider(
            application,taskRepository
        )

        taskViewModel = ViewModelProvider(
            this, viewModelProviderFactory
        ).get(TaskViewModel::class.java)

    }
}