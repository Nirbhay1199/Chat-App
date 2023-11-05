package com.nirbhay.chatapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.nirbhay.chatapp.Constants.Companion.IS_LOGIN
import com.nirbhay.chatapp.Constants.Companion.NAME
import com.nirbhay.chatapp.PreferenceManager
import com.nirbhay.chatapp.R
import com.nirbhay.chatapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (PreferenceManager.getBoolValue(IS_LOGIN)){
            val mainActivity = Intent(this, MainActivity::class.java)
            startActivity(mainActivity)
            finish()
        }

        binding.submit.setOnClickListener {
            if (binding.name.text.toString().trim().isEmpty()){
                Toast.makeText(this, "Enter your name to continue.", Toast.LENGTH_SHORT).show()
            }else{
                PreferenceManager.setBoolValue(IS_LOGIN, true)
                PreferenceManager.setStringValue(NAME, binding.name.text.toString())
                val mainActivity = Intent(this, MainActivity::class.java)
                startActivity(mainActivity)
                finish()
            }
        }

    }
}