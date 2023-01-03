package com.example.social_goal_sharing.ui.main.view.sign_in_up

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Telephony.Carriers.PASSWORD
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.social_goal_sharing.R
import com.example.social_goal_sharing.ui.main.view.MainActivity
import com.example.social_goal_sharing.ui.models.LoginModel
import com.example.social_goal_sharing.ui.utils.SharedPreference
import com.example.social_goal_sharing.ui.utils.Utility
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.Charset

const val PREF_NAME = "LOGIN_PREF_LOL"
const val LOGIN = "LOGIN"

class Sign_in : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    lateinit var cbRememberMe: CheckBox

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        title="Login"
        cbRememberMe = findViewById(R.id.rememberMeCB)

        val phone = findViewById<TextInputEditText>(R.id.phone)
        val password = findViewById<TextInputEditText>(R.id.password)
        val textviewsignup = findViewById<TextView>(R.id.textViewSignUp)
        val forgotpassword = findViewById<TextView>(R.id.forgotPassword)
        val btnlogin = findViewById<Button>(R.id.btnlogin)

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        if (sharedPreferences.getString(LOGIN, "")!!.isNotEmpty()){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        btnlogin.setOnClickListener(){
            val queue = Volley.newRequestQueue(this)
            val url = Utility.apiUrl + "/login"
            val requestBody = "&phone="+ URLEncoder.encode(phone.text.toString(),"UTF-8")+
                    "&password=" + password.text
            val stringRequest : StringRequest = object : StringRequest(Method.POST , url, com.android.volley.Response.Listener{
                    response -> Log.i("my log",response)
                val loginModel: LoginModel = Gson().fromJson(response, LoginModel::class.java)
                if (loginModel.status == "success"){
                    val preference = SharedPreference()
                    preference.setAccessToken(this,loginModel.accessToken)

                    if (cbRememberMe.isChecked){
                        sharedPreferences.edit().apply {
                            putString(LOGIN, phone.text.toString())
                            putString(PASSWORD, password.text.toString())
                            apply()
                        }
                    }

                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }else{
                    Utility.showAlert1(this,"Error",loginModel.message)
                }
            },com.android.volley.Response.ErrorListener{
                    error -> Log.i("my log",error.toString())
            }) {
                override fun getBody(): ByteArray {
                    return requestBody.toByteArray(Charset.defaultCharset())
                }
            }
            queue.add(stringRequest)
        }

        textviewsignup.setOnClickListener(){
            startActivity(Intent(this, Sign_up::class.java))
        }

        forgotpassword.setOnClickListener(){
            startActivity(Intent(this, ForgotPassDialog::class.java))
        }
    }
}