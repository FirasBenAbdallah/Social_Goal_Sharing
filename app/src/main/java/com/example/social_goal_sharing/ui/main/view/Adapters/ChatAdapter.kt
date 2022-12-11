package com.example.social_goal_sharing.ui.main.view.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.social_goal_sharing.R
import recycler.ChatList

class ChatAdapter (private val chatList : List<ChatList>):RecyclerView.Adapter<ChatAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.chat_user_items,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = chatList[position]
        holder.profileiconch!!.setImageResource(currentItem.profilIconChat)
        holder.namech!!.text = currentItem.nameChat
        holder.timech!!.text = currentItem.timeChat
        holder.msgch!!.text = currentItem.msg
    }

    override fun getItemCount(): Int = chatList.size

    class ViewHolder (itemView : View):RecyclerView.ViewHolder(itemView){

        var profileiconch : ImageView?=null
        var namech : TextView?=null
        var timech : TextView?=null
        var msgch : TextView?=null

        init {
            profileiconch = itemView.findViewById(R.id.profilIconChat)
            namech = itemView.findViewById(R.id.nameChat)
            timech = itemView.findViewById(R.id.timeChat)
            msgch = itemView.findViewById(R.id.msg)

        }
    }
}

