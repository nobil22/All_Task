package com.example.score_model

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.example.score_model.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel:ScorViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.score1.observe(this, Observer {
            binding.scoreA.text=it.toString()
        })
        viewModel.score2.observe(this, Observer {
            binding.scoreB.text=it.toString()
        })
        binding.add1.setOnClickListener{
            viewModel.incrementscore1()
        }
        binding.add2.setOnClickListener{
            viewModel.incrementscore2()
        }
    }
}