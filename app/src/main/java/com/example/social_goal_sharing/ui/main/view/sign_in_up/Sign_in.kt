package com.example.social_goal_sharing.ui.main.view.sign_in_up

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.social_goal_sharing.R
import com.example.social_goal_sharing.ui.base.ApiInterface
import com.example.social_goal_sharing.ui.base.Login
import com.example.social_goal_sharing.ui.main.view.MainActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates

class Sign_in : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val textviewsignup = findViewById<TextView>(R.id.textViewSignUp)
        val forgotpassword = findViewById<TextView>(R.id.forgotPassword)
        val btnlogin = findViewById<Button>(R.id.btnlogin)

        btnlogin.setOnClickListener(){
            login()
        }

        textviewsignup.setOnClickListener(){
            startActivity(Intent(this, Sign_up::class.java))
        }

        forgotpassword.setOnClickListener(){
            val view: View = getLayoutInflater().inflate(R.layout.activity_forgot_pass_dialog, null)
            val dialogBuilder = MaterialAlertDialogBuilder(this)

            dialogBuilder
                .setView(view)
                .show()
        }
    }
    private fun login(){
        val apiInterface = ApiInterface.create()

        val emailEdit = findViewById<TextInputEditText>(R.id.inputEmailET)
        val passwordEdit = findViewById<TextInputEditText>(R.id.Password)

        val map = HashMap<String, String>()
        map["email"] = emailEdit.text.toString()
        map["password"] = passwordEdit.text.toString()

        apiInterface.meth1(map).enqueue(object : Callback<Login> {

            override fun onResponse(call: Call<Login>, response:
            Response<Login>
            ) {
                if (response.code() == 200) {
                    /*val result = response.body()
                    val builder1 = AlertDialog.Builder(this@Sign_in)
                    builder1.setTitle(result!!.email)
                    builder1.setMessage("Login success")
                    builder1.show()*/
                    Toast.makeText(
                        this@Sign_in, "Login success",
                        Toast.LENGTH_LONG
                    ).show()
                    startActivity(Intent(this@Sign_in, MainActivity::class.java))
                } else if (response.code() == 404) {
                    Toast.makeText(
                        this@Sign_in, "Wrong Credentials",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<Login>, t: Throwable) {
                Toast.makeText(
                    this@Sign_in, t.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}