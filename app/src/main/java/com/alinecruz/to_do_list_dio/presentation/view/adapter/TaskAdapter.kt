package com.alinecruz.to_do_list_dio.presentation.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alinecruz.to_do_list_dio.R
import com.alinecruz.to_do_list_dio.databinding.ItemHomeTaskBinding
import com.alinecruz.to_do_list_dio.domain.entities.Task

class TaskAdapter : ListAdapter<Task, TaskAdapter.TaskViewHolder>(DiffCallback()) {

    var listenerEdit : (Task) -> Unit = {}
    var listenerDelete : (Task) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeTaskBinding.inflate(inflater, parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TaskViewHolder(private val binding: ItemHomeTaskBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(currentTask: Task) {
            binding.textHomeItemTaskTitle.text = currentTask.title
            binding.textHomeItemTaskData.text = "${currentTask.date} - ${currentTask.hour}"
            binding.constraintHomeItemTask.setOnClickListener {
                Log.e("TASK ADAPTER", "Clicou ${currentTask.description}")
            }
            binding.imageHomeItemTaskOptions.setOnClickListener {
                showOptions(currentTask)
            }
        }

        private fun showOptions(currentTask: Task) {
            val imageOptions = binding.imageHomeItemTaskOptions
            val popupOptions = PopupMenu(imageOptions.context, imageOptions)
            popupOptions.menuInflater.inflate(R.menu.popup_item_task_menu, popupOptions.menu)
            popupOptions.setOnMenuItemClickListener {
                when(it.itemId) {
                    R.id.itemTaskMenuEdit -> listenerEdit(currentTask)
                    R.id.itemTaskMenuDelete -> listenerDelete(currentTask)
                }
                return@setOnMenuItemClickListener true
            }
            popupOptions.show()
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean = oldItem == newItem
    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean = oldItem.id == newItem.id
}