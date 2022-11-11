package com.example.social_goal_sharing.ui.main.view.sign_in_up

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.social_goal_sharing.R
import com.example.social_goal_sharing.ui.main.view.MainActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class Sign_in : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val textviewsignup = findViewById<TextView>(R.id.textViewSignUp)
        val forgotpassword = findViewById<TextView>(R.id.forgotPassword)
        val btnlogin = findViewById<Button>(R.id.btnlogin)

        btnlogin.setOnClickListener(){
            startActivity(Intent(this, MainActivity::class.java))
        }

        textviewsignup.setOnClickListener(){
            startActivity(Intent(this, Sign_up::class.java))
        }

        forgotpassword.setOnClickListener(){
            // build alert dialog
            val view: View = getLayoutInflater().inflate(R.layout.activity_forgot_pass_dialog, null)
            val dialogBuilder = MaterialAlertDialogBuilder(this)

            // set message of alert dialog
            dialogBuilder
                .setView(view)
                .show()
        }
    }
}