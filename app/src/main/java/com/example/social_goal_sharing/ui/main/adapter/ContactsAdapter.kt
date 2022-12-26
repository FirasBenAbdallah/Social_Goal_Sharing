package com.example.social_goal_sharing.ui.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.social_goal_sharing.R
import com.example.social_goal_sharing.ui.models.User

class ContactsAdapter(
    private var contacts: ArrayList<User> = ArrayList()
): RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView= itemView.findViewById(R.id.name)
        val phone: TextView= itemView.findViewById(R.id.phone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
     val view: View = LayoutInflater.from(parent.context).inflate(R.layout.single_contact,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact: User = contacts[position]

        holder.name.text = contact.name
        holder.phone.text = contact.phone
    }

    override fun getItemCount(): Int {
        return contacts.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setData(contacts: ArrayList<User>){
        this.contacts.clear()
        this.contacts.addAll(contacts)
        notifyDataSetChanged()
    }
}