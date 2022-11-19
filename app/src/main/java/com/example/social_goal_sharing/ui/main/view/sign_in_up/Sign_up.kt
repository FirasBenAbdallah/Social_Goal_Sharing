package com.example.social_goal_sharing.ui.main.view.sign_in_up

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.social_goal_sharing.R
import com.example.social_goal_sharing.ui.base.ApiInterface
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Sign_up : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val btnsignup = findViewById<Button>(R.id.btnsignup)

        btnsignup.setOnClickListener{
                inscription()

        }
    }

    private fun inscription() {
        val apiInterface = ApiInterface.create()

        val nameEdit = findViewById<TextInputEditText>(R.id.inputPrenomUpET)
        val emailEdit = findViewById<TextInputEditText>(R.id.inputEmailUpET)
        val passwordEdit = findViewById<TextInputEditText>(R.id.inputPasswordET)
        val passwordconf = findViewById<TextInputEditText>(R.id.inputPasswordConfET)

        val passwordconfst = passwordconf.text.toString()
        val passwordEditst = passwordEdit.text.toString()
        val nameEditst = nameEdit.text.toString()
        val emailEditst = emailEdit.text.toString()

        val map = HashMap<String, String>()
        map["name"] = nameEditst
        map["email"] = emailEditst
        map["password"] = passwordEditst
        if (passwordEditst.isNotEmpty() && nameEditst.isNotEmpty() && emailEditst.isNotEmpty()) {
            if (passwordconfst == passwordEditst){
                apiInterface.executeSignup(map)?.enqueue(object : Callback<Void?> {

                    override fun onResponse(
                        call: Call<Void?>, response:
                        Response<Void?>
                    ) {
                        if (response.code() == 200) {
                            Toast.makeText(
                                this@Sign_up,
                                "Signed up successfully", Toast.LENGTH_LONG
                            ).show()
                            startActivity(Intent(this@Sign_up, Sign_in::class.java))
                        } else if (response.code() == 400) {
                            Toast.makeText(
                                this@Sign_up,
                                "Already registered", Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<Void?>, t: Throwable) {
                        Toast.makeText(
                            this@Sign_up, t.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                })
            } else {
                Toast.makeText(
                    this@Sign_up,
                    "Confirm password", Toast.LENGTH_LONG
                ).show()
            }
        } else {
            Toast.makeText(
                this@Sign_up,
                "You must fill all the fields",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}