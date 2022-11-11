package com.example.social_goal_sharing.ui.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.social_goal_sharing.R
import com.example.social_goal_sharing.ui.main.view.sign_in_up.Sign_up
import com.example.social_goal_sharing.ui.main.view.sign_in_up.Sign_in

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnsignup = findViewById<Button>(R.id.btnsignup1)
        val btnsignin = findViewById<Button>(R.id.btnsignin)

        btnsignup.setOnClickListener {
            startActivity(Intent(this, Sign_up::class.java))
        }
            btnsignin.setOnClickListener{
                startActivity(Intent(this, Sign_in::class.java))
        }
    }
}