package com.example.social_goal_sharing.ui.main.view.toolbar_fragments

import android.content.Intent
import android.icu.util.Calendar
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.social_goal_sharing.R
import com.example.social_goal_sharing.R.layout.fragment_add
import com.example.social_goal_sharing.databinding.FragmentAddBinding
import com.example.social_goal_sharing.ui.models.GeneralResponse
import com.example.social_goal_sharing.ui.utils.Utility
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import retrofit2.Response
import java.net.URLEncoder
import java.nio.charset.Charset


class Add : Fragment(fragment_add) {

    private var imageUri: Uri? = null
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private lateinit var eventName : TextInputEditText
    private lateinit var eventAddress : TextInputEditText
    private lateinit var eventStart : TextInputEditText
    private lateinit var eventEnd : TextInputEditText
    private lateinit var eventDesc : TextInputEditText
    private lateinit var imgAdd : ImageView
    private lateinit var btnadd : Button
    private lateinit var calendar: Calendar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v=inflater.inflate(fragment_add, container, false)
        imgAdd= v.findViewById(R.id.imgAdd)

        eventName = v.findViewById(R.id.inputNomev)
        eventAddress = v.findViewById(R.id.inputLieu)
        eventStart = v.findViewById(R.id.dateEvStart)
        eventEnd = v.findViewById(R.id.dateEvEnd)
        eventDesc = v.findViewById(R.id.inputDesc)
        btnadd = v.findViewById(R.id.btnAddEvent)

        //dateEv= v.findViewById(R.id.dateEv)
       calendar = Calendar.getInstance()
        imgAdd.setOnClickListener {
            startActivityForResult(
                Intent(
                    Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI
                ), 100
            )
        }

        btnadd.setOnClickListener{
            v -> addEvent()
        }

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddBinding.bind(view)
        binding.apply {
        dateEvStart.setOnClickListener {
            val datePickerFragment = DatePickerFrag()
            val supportFragmentManager = requireActivity().supportFragmentManager
            supportFragmentManager.setFragmentResultListener(
                "REQUEST_KEY",
                viewLifecycleOwner
            ) { resultKey, bundle ->
                if (resultKey == "REQUEST_KEY") {
                    val date = bundle.getString("SELECTED_DATE")
                        dateEvStart.setText(date)
                }
            }
            datePickerFragment.show(supportFragmentManager, "DatePickerFrag")
                }
            dateEvEnd.setOnClickListener {
                val datePickerFragment = DatePickerFrag()
                val supportFragmentManager = requireActivity().supportFragmentManager
                supportFragmentManager.setFragmentResultListener(
                    "REQUEST_KEY",
                    viewLifecycleOwner
                ) { resultKey, bundle ->
                    if (resultKey == "REQUEST_KEY") {
                        val date = bundle.getString("SELECTED_DATE")
                        dateEvEnd.setText(date)
                    }
                }
                datePickerFragment.show(supportFragmentManager, "DatePickerFrag")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK && requestCode == 100) {
            imageUri = data?.data
            imgAdd.setImageURI(imageUri)
        }
    }

    private fun addEvent() {
        val queue = Volley.newRequestQueue(context)
        val url : String = Utility.apiUrl + "/addevent"
        val requestBody : String = "name=" + eventName.text +
                "&address=" + eventAddress.text + "&start=" + eventStart.text +
                "&end=" + eventEnd.text + "&description=" + eventDesc.text +
                "&pdp=" + imgAdd.toString()
        val stringRequest : StringRequest = object : StringRequest(Method.POST , url , com.android.volley.Response.Listener{
                response ->
            val generalResponse: GeneralResponse = Gson().fromJson(response, GeneralResponse::class.java)
            context?.let { Utility.showAlert(it, "Add",generalResponse.message) }
        },com.android.volley.Response.ErrorListener{
                error -> Log.i("my log",error.message.toString())
        }) {
            override fun getBody(): ByteArray {
                return requestBody.toByteArray(Charset.defaultCharset())
            }
        }
        queue.add(stringRequest)
    }

    /*@SuppressLint("CutPasteId")
    private fun addEvent() {
        val apiInterface = EventApi.create()

        val eventNameSt = eventName.text.toString()
        val eventAddressSt = eventAddress.text.toString()
        val eventStartSt = eventStart.text.toString()
        val eventEndSt = eventEnd.text.toString()
        val eventDescSt = eventDesc.text.toString()

        val map = HashMap<String, String>()
        map["eventname"] = eventNameSt
        map["eventaddress"] = eventAddressSt
        map["eventstart"] = eventStartSt
        map["eventend"] = eventEndSt
        map["eventdesc"] = eventDescSt

//        if (eventNameSt.isNotEmpty()
//            && eventAddressSt.isNotEmpty()
//            && eventStartSt.isNotEmpty()
//            && eventEndSt.isNotEmpty()
//            && eventDescSt.isNotEmpty()) {
        apiInterface.executeEventAdd(map).enqueue(object : Callback<Void> {

            override fun onResponse(
                call: Call<Void>, response:
                Response<Void>
            ) {
                if (response.code() == 201) {
                    Toast.makeText(
                        activity,
                        "Event added successfully",
                        Toast.LENGTH_LONG
                    ).show()
                } else if (response.code() == 400) {
                    Toast.makeText(
                        activity,
                        "Already registered",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(
                    activity,
                    t.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        })
        *//*} else {
            Toast.makeText(
                activity,
                "You must fill all the fields",
                Toast.LENGTH_LONG
            ).show()
        }*//*
    }*/
}



