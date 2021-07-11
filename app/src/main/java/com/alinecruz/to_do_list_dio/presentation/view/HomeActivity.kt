package com.alinecruz.to_do_list_dio.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alinecruz.to_do_list_dio.R
import com.alinecruz.to_do_list_dio.databinding.ActivityHomeBinding
import com.alinecruz.to_do_list_dio.domain.entities.Task
import com.alinecruz.to_do_list_dio.presentation.view.adapter.TaskAdapter

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecycler()
        setupListener()
    }

    private fun setupListener() {
        binding.fabHomeAdd.setOnClickListener {
            val intent = Intent(this, NewTaskActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupRecycler() {
        adapter = TaskAdapter()
        binding.recyclerHomeTasks.adapter = adapter
    }
}