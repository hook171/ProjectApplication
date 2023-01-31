package com.example.project.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.project.databinding.TaskLayoutAdapterBinding
import com.example.project.fragments.HomeFragmentDirections
import com.example.project.model.Task
import java.util.*

class TaskAdapter: RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private var binding: TaskLayoutAdapterBinding? = null


    class TaskViewHolder(itemBinding: TaskLayoutAdapterBinding): RecyclerView.ViewHolder(itemBinding.root)


    private val differCallback =
        object : DiffUtil.ItemCallback<Task>(){
            override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
                return oldItem.id == newItem.id
            }

        }


    val differ = AsyncListDiffer(this,differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {

        val view = LayoutInflater.from(parent.context)

        binding = TaskLayoutAdapterBinding.inflate(
            view,
            parent,
            false
        )

        return TaskViewHolder(binding!!)

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentTask = differ.currentList[position]
        holder.itemView.apply{

            binding?.tvTaskTitle?.text = currentTask.nateTitle
            binding?.tvTaskBody?.text = currentTask.nateBody

            val random = Random()

            val color =
                Color.argb(
                    255, random.nextInt(256),
                    random.nextInt(256), random.nextInt(256)
                )

            binding?.ibColor?.setBackgroundColor(color)

        }.setOnClickListener{mView ->

            val direction = HomeFragmentDirections.actionHomeFragmentToUpdateTaskFragment(currentTask)

            mView.findNavController().navigate(direction)

        }
    }

}
