package com.example.social_goal_sharing.ui.main.view.toolbar_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.social_goal_sharing.R
import com.example.social_goal_sharing.ui.base.Event
import com.example.social_goal_sharing.ui.base.EventApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Profile : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_profile, container, false)

        return v
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Profile().apply {
                arguments = Bundle().apply {
                }
            }
    }

    /*private fun getEvent() {
        val apiInterface = EventApi.create()

        val map = HashMap<String, String>()

        apiInterface.getEvent(map).enqueue(object : Callback<Void> {

            override fun onResponse(
                call: Call<Void>, response:
                Response<Void>
            ) {
                if (response.code() == 200) {
                    Toast.makeText(
                        activity,
                        "Profile added successfully",
                        Toast.LENGTH_LONG
                    ).show()
                } else if (response.code() == 500) {
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
    }*/
}