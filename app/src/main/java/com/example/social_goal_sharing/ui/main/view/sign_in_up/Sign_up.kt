package com.example.social_goal_sharing.ui.main.view.sign_in_up

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.social_goal_sharing.R
import com.example.social_goal_sharing.ui.models.GeneralResponse
import com.example.social_goal_sharing.ui.utils.Utility
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.Charset
import java.util.*

class Sign_up : AppCompatActivity() {

    private lateinit var pdp:ImageView
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
    val name = findViewById<TextInputEditText>(R.id.inputPrenomUpET)
    val email = findViewById<TextInputEditText>(R.id.inputEmailUpET)
    val phone = findViewById<TextInputEditText>(R.id.phoneET)
    val birth_date = findViewById<TextInputEditText>(R.id.inputBirthUpET)
    val address = findViewById<TextInputEditText>(R.id.inputAddressUpET)
    val password = findViewById<TextInputEditText>(R.id.inputPasswordET)
    val conf_password = findViewById<TextInputEditText>(R.id.inputPasswordConfET)

        val calendar = Calendar.getInstance()
        pdp = findViewById<ImageView>(R.id.pdp)
        val btnsignup = findViewById<Button>(R.id.btnsignup)

        birth_date.setOnClickListener {
            DatePickerDialog(
                this,
                dateListener(birth_date, calendar),
                calendar.get(Calendar.YEAR),
                calendar.get(
                    Calendar.MONTH
                ),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
            birth_date.apply {
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
            val queue = Volley.newRequestQueue(applicationContext)
            val url : String = Utility.apiUrl + "/signup"
            val requestBody : String = "name=" + name.text +
                    "&phone="+ URLEncoder.encode(phone.text.toString(),"UTF-8")+
                    "&email=" + email.text + "&birth_date=" + birth_date.text +
                    "&address=" + address.text + "&password=" + password.text +
                    "&conf_password=" + conf_password.text + "&pdp=" + pdp.toString()
            val stringRequest : StringRequest = object : StringRequest(Method.POST , url , com.android.volley.Response.Listener{
                response ->
                val generalResponse: GeneralResponse = Gson().fromJson(response, GeneralResponse::class.java)
                Utility.showAlert1(this, "SignUp",generalResponse.message)
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

    fun setDate(input: EditText, calendar: Calendar) {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.FRANCE)
        input.setText(dateFormat.format(calendar.time))
    }

    fun dateListener(input: EditText, calendar: Calendar) =
        DatePickerDialog.OnDateSetListener { _, year, month, day ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, day)
            setDate(input, calendar)
        }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 100) {
            imageUri = data?.data
            pdp.setImageURI(imageUri)
        }
    }
}