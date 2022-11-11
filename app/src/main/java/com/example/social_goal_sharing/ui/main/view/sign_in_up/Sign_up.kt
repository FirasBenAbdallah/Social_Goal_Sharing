package com.example.social_goal_sharing.ui.main.view.sign_in_up

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.social_goal_sharing.R
import com.example.social_goal_sharing.ui.main.view.MainActivity

class Sign_up : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val btnsignup = findViewById<Button>(R.id.btnsignup)

        btnsignup.setOnClickListener{
            startActivity(Intent(this, Sign_in::class.java))
        }
    }
}