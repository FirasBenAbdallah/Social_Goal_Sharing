package com.example.social_goal_sharing.ui.main.view.sign_in_up

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.social_goal_sharing.R

class Sign_in : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val btnlogin = findViewById<Button>(R.id.btnlogin)
        btnlogin.setOnClickListener{

        }
    }
}