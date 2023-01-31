package com.example.project.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.project.MainActivity
import com.example.project.R
import com.example.project.databinding.FragmentNewTaskBinding
import com.example.project.model.Task
import com.example.project.toast
import com.example.project.viewmodel.TaskViewModel
import com.google.android.material.snackbar.Snackbar

class NewTaskFragment : Fragment(R.layout.fragment_new_task) {

    private var _binding: FragmentNewTaskBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var mView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNewTaskBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskViewModel = (activity as MainActivity).taskViewModel

        mView = view
    }

    private fun saveNote(view: View) {
        val taskTitle = binding.edTaskTitle.text.toString().trim()
        val taskBody = binding.edTaskBody.text.toString().trim()

        if (taskTitle.isNotEmpty()) {
            val note = Task(0, taskTitle, taskBody)

            taskViewModel.addTask(note)
            Snackbar.make(
                view, "Задача успешно сохранена...",
                Snackbar.LENGTH_SHORT
            ).show()
            view.findNavController().navigate(R.id.action_newTaskFragment_to_homeFragment)

        } else {
            activity?.toast("Пожалуйста введите название задачии...")
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save_menu -> {
                saveNote(mView)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.new_task_menu, menu)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}