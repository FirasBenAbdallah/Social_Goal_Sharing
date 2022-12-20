package com.example.social_goal_sharing.ui.main.view

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.social_goal_sharing.R
import com.example.social_goal_sharing.databinding.ActivityMainBinding
import com.example.social_goal_sharing.ui.main.view.sign_in_up.Sign_in
import com.example.social_goal_sharing.ui.main.view.toolbar_fragments.*
import com.example.social_goal_sharing.ui.models.GeneralResponse
import com.example.social_goal_sharing.ui.models.GetUserModel
import com.example.social_goal_sharing.ui.models.User
import com.example.social_goal_sharing.ui.utils.SharedPreference
import com.example.social_goal_sharing.ui.utils.Utility
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import java.net.URLEncoder
import java.nio.charset.Charset


class MainActivity : AppCompatActivity() {

    lateinit var sharedPreference: SharedPreference
    lateinit var user: User
    private lateinit var binding: ActivityMainBinding
    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        sharedPreference = SharedPreference()
        fragmentTransaction.replace(R.id.frame,fragment)
        fragmentTransaction.commitNow()
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        replaceFragment(Home())

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnlogout = findViewById<ImageButton>(R.id.logoutIcon)
        btnlogout.setOnClickListener(){
            doLogout()
           // finishAffinity()
        }
        binding.bottomnavigationtoolbar1.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.logoutIcon -> {
                    doLogout()
                    //finishAffinity()
                }
                else -> {}
            }
            true
        }
        binding.bottomnavigationtoolbar2.setOnItemSelectedListener {

            when(it.itemId){
                R.id.homeIcon -> replaceFragment(Home())
                R.id.profilIcon -> replaceFragment(Profile())
                R.id.plusIcon -> replaceFragment(Add())
                R.id.searchIcon -> replaceFragment(LookFor())
                R.id.messageIcon -> replaceFragment(Discussion())
                else -> {}
            }
            true
        }
        getData()
    }

    fun doLogout() {
        val queue = Volley.newRequestQueue(this)
        val url = Utility.apiUrl + "/logout"
        val stringRequest : StringRequest = object : StringRequest(Method.POST, url,Response.Listener { response ->
            val generalResponse: GeneralResponse = Gson().fromJson(response, GeneralResponse::class.java)
            Utility.showAlert(this,"Logout",generalResponse.message, Runnable {
                if (generalResponse.status == "success"){
                    sharedPreference.removeAccessToken(this)
                    startActivity(Intent(this, Sign_in::class.java))
                    finish()
                }
            })
        },Response.ErrorListener {
            error ->
        }){
            override fun getHeaders(): MutableMap<String, String> {
                val headers: HashMap<String,String> = HashMap()
                headers["Authorization"] = "Bearer "+sharedPreference.getAccessToken(applicationContext)
                return headers
            }
        }
        queue.add(stringRequest)
    }

    fun getData(){
        val queue = Volley.newRequestQueue(this)
        val url = Utility.apiUrl + "/getUser"
        val stringRequest : StringRequest = object : StringRequest(Method.POST, url,Response.Listener { response ->
            val getUserModel: GetUserModel = Gson().fromJson(response, GetUserModel::class.java)
            if (getUserModel.status == "success"){
                user=getUserModel.user
                getContactsPermission()
            } else {
                Utility.showAlert( this, "Error", getUserModel.message)
            }


        },Response.ErrorListener { error ->  

        }){
            override fun getHeaders(): MutableMap<String, String> {
                val headers: HashMap<String,String> = HashMap()
                headers["authorization"] = "Bearer "+sharedPreference.getAccessToken(applicationContext)
                return headers
            }
        }
        queue.add(stringRequest)
    }

    fun getContactsPermission() {
        val permission = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.READ_CONTACTS
        )
        if (permission == PackageManager.PERMISSION_GRANTED) {
            getContacts()
        }else {
            ActivityCompat.requestPermissions(this,
           arrayOf(android.Manifest.permission.READ_CONTACTS),
            565)
        }
    }

    @SuppressLint("Range")
    fun getContacts(){
        val contacts : JSONArray = JSONArray()
        val phones = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
        )
        while (phones?.moveToNext() == true){
            val name:String = phones.getString(
                phones.getColumnIndex(
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                )
            )
            val phone : String = phones.getString(
                phones.getColumnIndex(
                    ContactsContract.CommonDataKinds.Phone.NUMBER
                )
            )
            val obj: JSONObject = JSONObject()
            obj.put("name",name)
            obj.put("phone",phone)
            contacts.put(obj)
        }
        phones?.close()
        Log.i("mylog",contacts.toString())
        val queue = Volley.newRequestQueue(this)
        val url = Utility.apiUrl + "/contacts/save"
        val requestBody = "contacts=" + URLEncoder.encode(contacts.toString(),"UTF-8")

        val stringRequest : StringRequest = object : StringRequest(
                    Method.POST, url,
                Response.Listener {
                    response ->
                    val  generalResponse : GeneralResponse = Gson().fromJson(response, GeneralResponse::class.java)
                    Utility.showAlert(this,"Save contacts", generalResponse.message)
                },Response.ErrorListener { error ->

            }
        ){
            override fun getHeaders(): MutableMap<String, String> {
                val headers : HashMap<String, String> = HashMap()
                headers["autorization"] = "Bearer" + sharedPreference.getAccessToken(applicationContext)
                return headers
            }

            override fun getBody(): ByteArray {
                return requestBody.toByteArray(Charset.defaultCharset())
            }
        }
        queue.add(stringRequest)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == 565) {
            if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED){

            }else {
                getContacts()
            }
        }
    }
}