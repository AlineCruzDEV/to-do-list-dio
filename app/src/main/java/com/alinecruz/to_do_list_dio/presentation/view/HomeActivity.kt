package com.alinecruz.to_do_list_dio.presentation.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.alinecruz.to_do_list_dio.databinding.ActivityHomeBinding
import com.alinecruz.to_do_list_dio.datasource.TaskDataSource
import com.alinecruz.to_do_list_dio.presentation.view.adapter.TaskAdapter

class HomeActivity : AppCompatActivity() {

    companion object {
        private const val CREATE_NEW_TASK = 1000
    }

    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecycler()
        updateListAdapter()
        setupListener()
    }

    private fun setupListener() {
        binding.fabHomeAdd.setOnClickListener {
            val intent = Intent(this, NewTaskActivity::class.java)
            startActivityForResult(intent, CREATE_NEW_TASK)
        }
        adapter.listenerEdit = {
            val intent = Intent(this, NewTaskActivity::class.java)
            intent.putExtra(NewTaskActivity.ID_TASK, it.id)
            startActivityForResult(intent, CREATE_NEW_TASK)
        }
        adapter.listenerDelete = {
            TaskDataSource.deleteTask(it)
            updateListAdapter()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CREATE_NEW_TASK && resultCode == Activity.RESULT_OK) {
            updateListAdapter()
        }
    }

    private fun updateListAdapter() {
        val listTasks = TaskDataSource.getList()

        binding.includeHomeEmpty.constraintHomeItemEmpty.visibility =
            if (listTasks.isEmpty()) View.VISIBLE else View.GONE

        adapter.submitList(listTasks)
    }

    private fun setupRecycler() {
        adapter = TaskAdapter()
        binding.recyclerHomeTasks.adapter = adapter
    }
}