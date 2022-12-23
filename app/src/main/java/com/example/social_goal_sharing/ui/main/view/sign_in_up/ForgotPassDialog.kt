package com.example.social_goal_sharing.ui.main.view.sign_in_up

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.ChangeBounds
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.social_goal_sharing.R
import com.example.social_goal_sharing.ui.base.ApiInterface
import com.example.social_goal_sharing.ui.base.Login
import com.example.social_goal_sharing.ui.main.view.MainActivity
import com.example.social_goal_sharing.ui.models.GeneralResponse
import com.example.social_goal_sharing.ui.models.LoginModel
import com.example.social_goal_sharing.ui.utils.SharedPreference
import com.example.social_goal_sharing.ui.utils.Utility
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URLEncoder
import java.nio.charset.Charset
import kotlin.math.log

class ForgotPassDialog : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass_dialog)

        val email = findViewById<TextInputEditText>(R.id.changeEmail)
        val code = findViewById<TextInputEditText>(R.id.changepasscode)
        val password = findViewById<TextInputEditText>(R.id.changepassnew)
        val btnsendcode = findViewById<Button>(R.id.sendCodeBtn)
        val btncofpass = findViewById<Button>(R.id.changepassBtn)

        btnsendcode.setOnClickListener(){
            val queue = Volley.newRequestQueue(this)
            val url = Utility.apiUrl + "/sendPasswordRecoveryEmail"
            val requestBody = "&email=" + email.text
            val stringRequest : StringRequest = object : StringRequest(Method.POST , url, com.android.volley.Response.Listener{
                    response -> Log.i("my log",response)
                val generalResponse: GeneralResponse = Gson().fromJson(response, GeneralResponse::class.java)
                Utility.showAlert1(this, "Send Code", generalResponse.message)
            },com.android.volley.Response.ErrorListener{
                    error -> Log.i("my log",error.message.toString())
            }) {
                override fun getBody(): ByteArray {
                    return requestBody.toByteArray(Charset.defaultCharset())
                }
            }
            queue.add(stringRequest)
//            ChangePassWord()
        }

        btncofpass.setOnClickListener(){
            val queue = Volley.newRequestQueue(this)
            val url = Utility.apiUrl + "/resetPassword"
            val requestBody = "&email=" + email.text + "&code=" + code.text + "&password=" + password.text
            val stringRequest : StringRequest = object : StringRequest(Method.POST , url, com.android.volley.Response.Listener{
                    response -> Log.i("my log",response)
                val generalResponse: GeneralResponse = Gson().fromJson(response, GeneralResponse::class.java)
                Utility.showAlert1(this, "Change Password", generalResponse.message)
            },com.android.volley.Response.ErrorListener{
                    error -> Log.i("my log",error.message.toString())
            }) {
                override fun getBody(): ByteArray {
                    return requestBody.toByteArray(Charset.defaultCharset())
                }
            }
            queue.add(stringRequest)
        }
    }
    /*private fun ChangePassWord(){
        val apiInterface = ApiInterface.create()
        val newpass = findViewById<TextInputEditText>(R.id.changepassnew)
        val confnewpass = findViewById<TextInputEditText>(R.id.changepassconf)
        val emailEdit = findViewById<TextInputEditText>(R.id.changeEmail)
        val email = emailEdit.text.toString()

        val map = HashMap<String, String>()
        map["password"] = newpass.text.toString()
        map["confpassword"] = confnewpass.text.toString()

        if (newpass.text.toString() == confnewpass.text.toString()) {
            apiInterface.changePass(email, map).enqueue(object : Callback<Void> {

                override fun onResponse(
                    call: Call<Void>, response:
                    Response<Void>
                ) {
                    if (response.code() == 200) {
                        Toast.makeText(
                            this@ForgotPassDialog, "PassWord change success",
                            Toast.LENGTH_LONG
                        ).show()
                        startActivity(Intent(this@ForgotPassDialog, Sign_in::class.java))
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
        }else {
            Toast.makeText(
                this@ForgotPassDialog,
                "Confirm password", Toast.LENGTH_LONG
            ).show()
        }
    }*/
}