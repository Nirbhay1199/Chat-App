package com.nirbhay.chatapp.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.nirbhay.chatapp.R
import com.nirbhay.chatapp.activities.ChatMessage

class ChatListAdapter(private val context: Context, private val socketId: String, private val chatList: ArrayList<ChatMessage>): RecyclerView.Adapter<ChatListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val chat: TextView = itemView.findViewById(R.id.chat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.chat_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chatItem = chatList[position]

        if (chatItem.id == socketId){
            holder.chat.text = chatItem.message
            holder.chat.backgroundTintList = ContextCompat.getColorStateList(context, R.color.msgBlue)
        }else{
            holder.chat.text = chatItem.message
            holder.chat.backgroundTintList = ContextCompat.getColorStateList(context, R.color.msgGreen)
        }



    }
}