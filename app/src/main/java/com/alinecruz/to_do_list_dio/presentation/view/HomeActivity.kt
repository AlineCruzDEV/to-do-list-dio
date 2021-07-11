package com.alinecruz.to_do_list_dio.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alinecruz.to_do_list_dio.R
import com.alinecruz.to_do_list_dio.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setupRecycler()
        //setupListener()
    }

    private fun setupListener() {
        TODO("Not yet implemented")
    }

    private fun setupRecycler() {
        TODO("Not yet implemented")
    }
}