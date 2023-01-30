package com.example.project.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.project.R
import com.example.project.databinding.FragmentNewTaskBinding

class NewTaskFragment : Fragment(R.layout.fragment_new_task) {

    private var _binding: FragmentNewTaskBinding? = null
    private val binding get() = _binding!!

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