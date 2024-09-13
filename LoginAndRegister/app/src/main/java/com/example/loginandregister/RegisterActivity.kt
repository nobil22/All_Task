package com.example.loginandregister

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginandregister.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private val binding by lazy(LazyThreadSafetyMode.NONE){
        ActivityRegisterBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.username.editTx.hint="Enter username"
        binding.email.editTx.hint="Enter Email"
        binding.password.editTx.hint="Enter password"
        binding.backbuttonp.setOnClickListener {
            var myint= Intent(this,MainActivity::class.java)
            startActivity(myint)
        }
    }
}