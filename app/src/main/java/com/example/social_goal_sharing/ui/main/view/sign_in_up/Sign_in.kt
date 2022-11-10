package com.example.social_goal_sharing.ui.main.view.sign_in_up

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.social_goal_sharing.R
import com.example.social_goal_sharing.ui.main.view.MainActivity

class Sign_in : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val textviewsignup = findViewById<TextView>(R.id.textViewSignUp)
        val btnlogin = findViewById<Button>(R.id.btnlogin)

        btnlogin.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }

        textviewsignup.setOnClickListener(){
            startActivity(Intent(this, Sign_up::class.java))
        }
    }
}