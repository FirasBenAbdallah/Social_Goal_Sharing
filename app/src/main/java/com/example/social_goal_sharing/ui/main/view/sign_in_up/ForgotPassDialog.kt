package com.example.social_goal_sharing.ui.main.view.sign_in_up

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.ChangeBounds
import android.widget.Button
import android.widget.Toast
import com.example.social_goal_sharing.R
import com.example.social_goal_sharing.ui.base.ApiInterface
import com.example.social_goal_sharing.ui.base.Login
import com.example.social_goal_sharing.ui.main.view.MainActivity
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class ForgotPassDialog : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass_dialog)

        val btncofpass = findViewById<Button>(R.id.changepassBtn)

        btncofpass.setOnClickListener(){
            ChangePassWord()
        }
    }
    private fun ChangePassWord(){
        val apiInterface = ApiInterface.create()
        val newpass = findViewById<TextInputEditText>(R.id.changepassnew)
        val confnewpass = findViewById<TextInputEditText>(R.id.changepassconf)
        val emailEdit = findViewById<TextInputEditText>(R.id.changeEmail)

        val map = HashMap<String, String>()
        map["email"] = emailEdit.text.toString()
        map["password"] = newpass.text.toString()
        map["confpassword"] = confnewpass.text.toString()

        apiInterface.changePass(map).enqueue(object : Callback<Void> {

            override fun onResponse(call: Call<Void>, response:
            Response<Void>
            ) {
                if (response.code() == 200) {
                    Toast.makeText(
                        this@ForgotPassDialog, "PassWord change success",
                        Toast.LENGTH_LONG
                    ).show()
                    //startActivity(Intent(this@ForgotPassDialog, MainActivity::class.java))
                } else if (response.code() == 500) {
                    Toast.makeText(
                        this@ForgotPassDialog, "Wrong Credentials",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(
                    this@ForgotPassDialog, t.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}