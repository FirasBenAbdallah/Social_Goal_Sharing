package com.example.social_goal_sharing.ui.main.view.sign_in_up

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.social_goal_sharing.R
import com.example.social_goal_sharing.ui.base.ApiInterface
import com.example.social_goal_sharing.ui.main.view.toolbar_fragments.DatePickerFrag
import com.google.android.material.internal.ViewUtils.hideKeyboard
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.HashMap

class Sign_up : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val calendar = Calendar.getInstance()
        val pdp = findViewById<ImageView>(R.id.pdp)
        val btnsignup = findViewById<Button>(R.id.btnsignup)
        val birthEt = findViewById<TextInputEditText>(R.id.inputBirthUpET)

        birthEt.setOnClickListener {
            DatePickerDialog(
                this,
                dateListener(birthEt, calendar),
                calendar.get(Calendar.YEAR),
                calendar.get(
                    Calendar.MONTH
                ),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
            birthEt.apply {
                clearFocus()
             //   hideKeyboard()
            }
        }

        pdp.setOnClickListener {
            startActivityForResult(
                Intent(
                    Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI
                ), 100
            )
        }
        btnsignup.setOnClickListener{
                inscription()

        }
    }

    @SuppressLint("CutPasteId")
    private fun inscription() {
        val apiInterface = ApiInterface.create()

        val fnameEdit = findViewById<TextInputEditText>(R.id.inputPrenomUpET)
        val lnameEdit = findViewById<TextInputEditText>(R.id.inputenomUpET)
        val emailEdit = findViewById<TextInputEditText>(R.id.inputEmailUpET)
        val birthEdit = findViewById<TextInputEditText>(R.id.inputBirthUpET)
        val addressEdit = findViewById<TextInputEditText>(R.id.inputAddressUpET)
        val passwordEdit = findViewById<TextInputEditText>(R.id.inputPasswordET)
        val passwordconf = findViewById<TextInputEditText>(R.id.inputPasswordConfET)
        val pdp = findViewById<ImageView>(R.id.pdp)

        val pdpst = pdp.drawable.toString()
        val fnameEditst = fnameEdit.text.toString()
        val lnameEditst = lnameEdit.text.toString()
        val emailEditst = emailEdit.text.toString()
        val birthEditst = birthEdit.text.toString()
        val addressEditst = addressEdit.text.toString()
        val passwordEditst = passwordEdit.text.toString()
        val passwordconfst = passwordconf.text.toString()

        val map = HashMap<String, String>()
        map["firstname"] = fnameEditst
        map["lastname"] = lnameEditst
        map["email"] = emailEditst
        map["birthdate"] = birthEditst
        map["address"] = addressEditst
        map["password"] = passwordEditst
        map["confpassword"] = passwordconfst
        map["photoDeProfile"] = pdpst

        if (fnameEditst.isNotEmpty() && lnameEditst.isNotEmpty() && emailEditst.isNotEmpty() && birthEditst.isNotEmpty() && addressEditst.isNotEmpty() && passwordEditst.isNotEmpty() && pdpst.isNotEmpty()) {
            if (passwordconfst == passwordEditst){
                apiInterface.executeSignup(map).enqueue(object : Callback<Void> {

                    override fun onResponse(
                        call: Call<Void>, response:
                        Response<Void>
                    ) {
                        if (response.code() == 201) {
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

                    override fun onFailure(call: Call<Void>, t: Throwable) {
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
    fun setDate(input: EditText, calendar: Calendar) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE)
        input.setText(dateFormat.format(calendar.time))
    }
    fun dateListener(input: EditText, calendar: Calendar) =
        DatePickerDialog.OnDateSetListener { _, year, month, day ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, day)
            setDate(input, calendar)
        }
}