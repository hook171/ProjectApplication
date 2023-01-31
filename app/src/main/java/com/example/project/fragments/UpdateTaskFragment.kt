package com.example.project.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.project.MainActivity
import com.example.project.R
import com.example.project.adapter.TaskAdapter
import com.example.project.databinding.FragmentUpdateTaskBinding
import com.example.project.model.Task
import com.example.project.toast
import com.example.project.viewmodel.TaskViewModel

class UpdateTaskFragment : Fragment() {

    private var _binding: FragmentUpdateTaskBinding? = null
    private val binding get() = _binding!!


    private val args: UpdateTaskFragmentArgs by navArgs()
    private lateinit var currentTask: Task
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUpdateTaskBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskViewModel = (activity as MainActivity).taskViewModel

        currentTask = args.task!!

        binding.edTaskBodyUpdate.setText(currentTask.nateBody)
        binding.edTaskTitleUpdate.setText(currentTask.nateTitle)

        binding.fabUpdate.setOnClickListener{

            val title = binding.edTaskTitleUpdate.text.toString().trim()
            val body = binding.edTaskBodyUpdate.text.toString().trim()

            if(title.isNotEmpty()){
                val task = Task(currentTask.id, title, body)
                taskViewModel.updateTask(task)

                view.findNavController().navigate(
                    R.id.action_updateTaskFragment_to_homeFragment
                )
            }
            else{
                activity?.toast("Пожалуйста введите название записи...")
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.update_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}