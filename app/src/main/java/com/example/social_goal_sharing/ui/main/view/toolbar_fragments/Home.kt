package com.example.social_goal_sharing.ui.main.view.toolbar_fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.social_goal_sharing.R
import com.example.social_goal_sharing.ui.models.GeneralResponse
import com.example.social_goal_sharing.ui.utils.Utility
import com.google.gson.Gson
import recycler.Acc
import recycler.HomeAdapter


class Home : Fragment() {

lateinit var recyclerView: RecyclerView
/*lateinit var adapter: HomeAdapter
lateinit var homeitemsList : ArrayList<Acc>*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

   override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
        // Inflate the layout for this fragment
        = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        super.onViewCreated(v, savedInstanceState)
        val r = v.findViewById<RecyclerView>(R.id.recycleHome)

        val list = arrayListOf<Acc>(
            Acc(R.drawable.ic_baseline_account,"FirasBA","3h",R.drawable.fifa,"slouma",R.drawable.icons8_heart_24,R.drawable.ic_baseline_message_24,R.drawable.icons8_share_24),
            Acc(R.drawable.ic_baseline_account,"Firas","3h",R.drawable.do_white,"slouma,aymen",R.drawable.icons8_heart_24,R.drawable.ic_baseline_message_24,R.drawable.icons8_share_24),
            Acc(R.drawable.ic_baseline_account,"FBA","30mn",R.drawable.env,"slouma",R.drawable.icons8_heart_24,R.drawable.ic_baseline_message_24,R.drawable.icons8_share_24),
            Acc(R.drawable.ic_baseline_account,"Slouma","1j",R.drawable.ph2,"slouma",R.drawable.icons8_heart_24,R.drawable.ic_baseline_message_24,R.drawable.icons8_share_24),
            Acc(R.drawable.ic_baseline_account,"Echebbi","3h",R.drawable.do_white,"slouma",R.drawable.icons8_heart_24,R.drawable.ic_baseline_message_24,R.drawable.icons8_share_24)
        )
        r.adapter = HomeAdapter(list)
        r.layoutManager = LinearLayoutManager(v.context)
        val home = v.findViewById<Button>(R.id.btnhome)
        home.setOnClickListener(){
            getEvent()
        }
    }
    private fun getEvent() {
        val queue = Volley.newRequestQueue(context)
        val url : String = Utility.apiUrl + "/getevent"
        val stringRequest : StringRequest = object : StringRequest(Method.GET , url , com.android.volley.Response.Listener{
                response ->
            val generalResponse: GeneralResponse = Gson().fromJson(response, GeneralResponse::class.java)
            val json =generalResponse.message
            context?.let { Utility.showAlert1(it, "Get","${generalResponse.status} : ${json} ") }

        },com.android.volley.Response.ErrorListener{
                error -> Log.i("my log",error.message.toString())
        }) {
        }
        queue.add(stringRequest)
    }
}