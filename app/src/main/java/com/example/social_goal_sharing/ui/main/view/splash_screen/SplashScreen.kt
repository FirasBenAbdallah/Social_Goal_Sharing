package com.example.social_goal_sharing.ui.main.view.splash_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.social_goal_sharing.R
import com.example.social_goal_sharing.ui.main.view.MainActivity
import com.example.social_goal_sharing.ui.main.view.sign_in_up.Sign_in

class SplashScreen : AppCompatActivity() {
    private val SPLASH_TIME_OUT: Long = 2000 //3 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, Sign_in::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }
}
