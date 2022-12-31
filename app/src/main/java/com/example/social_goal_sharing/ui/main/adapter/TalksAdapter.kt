package com.example.social_goal_sharing.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.social_goal_sharing.R
import com.example.social_goal_sharing.ui.main.interfaces.AttachmentInterface
import com.example.social_goal_sharing.ui.models.Message
import com.example.social_goal_sharing.ui.utils.FetchImageFromInternet

class TalksAdapter (
    private var messages: ArrayList<Message> = ArrayList(),
    private var attachmentInterface: AttachmentInterface
        ) : RecyclerView.Adapter<TalksAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val sender : TextView = itemView.findViewById(R.id.sender)
        val message : TextView = itemView.findViewById(R.id.message)
        val attachment : ImageView = itemView.findViewById(R.id.attachment)
        val downloadAttachment : ImageView = itemView.findViewById(R.id.downloadAttachment)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val view : View =  LayoutInflater.from(parent.context).inflate(R.layout.single_message,parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message : Message = messages[position]
        holder.sender.text = message.sender.name
        holder.message.text = message.message


        if (message.attachment.isEmpty()){
            holder.attachment.isVisible = false
            holder.downloadAttachment.isVisible = false
        } else {
            holder.attachment.isVisible = true
            holder.downloadAttachment.isVisible = true

            FetchImageFromInternet(holder.attachment).execute(message.attachment)

            holder.downloadAttachment.setOnClickListener{
                attachmentInterface.onDownload(holder.itemView)
            }
            }

    }

    override fun getItemCount(): Int {
        return messages.size
    }
    fun getData(): ArrayList<Message>{
        return this.messages
    }
    fun appendData(message: Message) {
        this.messages.add(message)
        notifyItemInserted(this.messages.size)
    }
    fun setData(messages: ArrayList<Message>){
        this.messages.clear()
        this.messages.addAll(messages)
        notifyDataSetChanged()
    }
}