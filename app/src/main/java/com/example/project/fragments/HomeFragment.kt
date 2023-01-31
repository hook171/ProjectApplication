package com.example.project.fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.room.util.query
import com.example.project.MainActivity
import com.example.project.R
import com.example.project.adapter.TaskAdapter
import com.example.project.databinding.FragmentHomeBinding
import com.example.project.model.Task
import com.example.project.viewmodel.TaskViewModel

class HomeFragment : Fragment(R.layout.fragment_home),
    SearchView.OnQueryTextListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(
            inflater,
            container,
            false

        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskViewModel = (activity as MainActivity).taskViewModel
        setUpRecyclerView()

        binding.fabAddTask.setOnClickListener{
            it.findNavController().navigate(R.id.action_homeFragment_to_newTaskFragment)
        }

    }

    private fun setUpRecyclerView(){
        taskAdapter = TaskAdapter()

        binding.recyclerView.apply{

             layoutManager = StaggeredGridLayoutManager(
                 1,
                 StaggeredGridLayoutManager.VERTICAL
             )

            adapter = taskAdapter

        }

        activity?.let {
            taskViewModel.getAllTask().observe(viewLifecycleOwner, {
                    task ->  taskAdapter.differ.submitList(task)
                    updateUI(task)
            })
        }

    }

    private fun updateUI(task: List<Task>) {
        if (task.isNotEmpty()) {
            binding.recyclerView.visibility = View.VISIBLE
            binding.cardView.visibility = View.GONE
        } else {
            binding.cardView.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
        }
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.home_menu,menu)

        val mMenuSearch = menu.findItem(R.id.menu_search).actionView as SearchView
        mMenuSearch.isSubmitButtonEnabled = true
        mMenuSearch.setOnQueryTextListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(query != null){
            searchTasks(query)
        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if(query != null){
            searchTasks(query)
        }
        return true
    }


    private fun searchTasks(query: String?){
        val searchQuery = "%$query%"

        taskViewModel.searchTask(searchQuery).observe(this,{list ->

            taskAdapter.differ.submitList(list)
        })
    }


}