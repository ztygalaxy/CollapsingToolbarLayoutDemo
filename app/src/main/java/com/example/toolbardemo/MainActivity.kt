package com.example.toolbardemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.toolbardemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnA1.setOnClickListener{
            val intent = Intent(this, ScrollActivity1::class.java)
            startActivity(intent)
        }

        binding.btnA2.setOnClickListener{
            val intent = Intent(this, ScrollActivity2::class.java)
            startActivity(intent)
        }

    }
}