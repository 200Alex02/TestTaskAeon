package com.example.testtaskaeon.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testtaskaeon.R
import com.example.testtaskaeon.databinding.ActivityMainBinding
import com.example.testtaskaeon.presentation.fragments.LoginFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, LoginFragment())
            .commit()
    }
}