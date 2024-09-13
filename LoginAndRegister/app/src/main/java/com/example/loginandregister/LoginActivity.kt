package com.example.loginandregister

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginandregister.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private val binding by lazy(LazyThreadSafetyMode.NONE){
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.email.editTx.hint="Enter Email"
        binding.password.editTx.hint="Enter paasword"
        binding.backbuttonp.setOnClickListener {
         var myint= Intent(this,MainActivity::class.java)

            startActivity(myint)
        }
        binding.loginbuttonp.setOnClickListener {
            var myint2=Intent(this,MainActivity2::class.java)
            myint2.putExtra("name","mohamed")
            startActivity(myint2)
        }
    }
}