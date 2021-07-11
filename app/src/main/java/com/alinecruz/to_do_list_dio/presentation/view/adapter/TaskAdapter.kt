package com.alinecruz.to_do_list_dio.presentation.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alinecruz.to_do_list_dio.databinding.ItemHomeTaskBinding
import com.alinecruz.to_do_list_dio.domain.entities.Task

class TaskAdapter() : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    var tasksList = mutableListOf<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeTaskBinding.inflate(inflater, parent, false)
        return TaskViewHolder(binding)
    }

    override fun getItemCount() = tasksList.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentTask = tasksList[position]
        holder.bind(currentTask)
    }

    class TaskViewHolder(private val binding: ItemHomeTaskBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(currentTask: Task) {
            binding.textHomeItemTaskTitle.text = currentTask.title
            binding.textHomeItemTaskData.text = "${currentTask.date} - ${currentTask.hour}"
            binding.constraintHomeItemTask.setOnClickListener {
                Log.e("TASK ADAPTER", "Clicou ${currentTask.description}")
            }
        }

    }
}