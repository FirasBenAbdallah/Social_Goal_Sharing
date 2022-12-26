package com.example.social_goal_sharing.ui.main.view.toolbar_fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.social_goal_sharing.R
import com.example.social_goal_sharing.ui.main.adapter.ContactsAdapter
import com.example.social_goal_sharing.ui.models.FetchContactsModel
import com.example.social_goal_sharing.ui.models.User
import com.example.social_goal_sharing.ui.utils.SharedPreference
import com.example.social_goal_sharing.ui.utils.Utility
import com.google.gson.Gson

class ContactsFragment: Fragment() {

    lateinit var sharedPreference: SharedPreference
    lateinit var contacts: ArrayList<User>
    lateinit var rv : RecyclerView
    lateinit var adapter: ContactsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate (R.layout.fragment_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv= view.findViewById(R.id.rv)
        rv.layoutManager = LinearLayoutManager(context)

        adapter= ContactsAdapter(ArrayList())

        rv.adapter = adapter
    }
    fun getData() {
        val queue = Volley.newRequestQueue(context)
        val url = Utility.apiUrl + "/contacts/fetch"
        val stringRequest: StringRequest = object : StringRequest(
            Method.POST, url,
            Response.Listener { response ->
              //  Log.i("myLog", response)
                val fetchContactsModel:FetchContactsModel = Gson().fromJson(response, FetchContactsModel::class.java)
                if (fetchContactsModel.status=="success"){
                    contacts= fetchContactsModel.contacts
                    adapter.setData(contacts)
                }else{
                   context?.let {
                       Utility.showAlert(it, "Error", fetchContactsModel.message)
                   }
                }
            },
            Response.ErrorListener { error ->
                //
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers: HashMap<String, String> = HashMap()
                headers["Authorization"] = "Bearer " + context?.let {
                    sharedPreference.getAccessToken(it)
                }
                return headers
            }
        }
        queue.add(stringRequest)
    }
    override fun onResume() {
        super.onResume()

        contacts= ArrayList()
        sharedPreference = SharedPreference()
        getData()
    }
}