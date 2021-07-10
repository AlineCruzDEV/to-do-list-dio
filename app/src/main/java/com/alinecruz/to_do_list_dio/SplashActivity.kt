package com.alinecruz.to_do_list_dio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alinecruz.to_do_list_dio.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}