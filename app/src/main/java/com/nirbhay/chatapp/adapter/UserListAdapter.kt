package com.nirbhay.chatapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.recyclerview.widget.RecyclerView
import com.nirbhay.chatapp.R
import com.nirbhay.chatapp.activities.ChatActivity

class UserListAdapter(private val userList: ArrayList<Any>) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    private var adapterCallback: AdapterCallback? = null

    fun setAdapterCallback(callback: AdapterCallback){
        adapterCallback = callback
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName: TextView = itemView.findViewById(R.id.userName)
        val l1 : LinearLayout = itemView.findViewById(R.id.l1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]

        holder.userName.text = user.toString()

        holder.l1.setOnClickListener {
            adapterCallback?.startChatActivity(user.toString())

        }

    }
}

interface AdapterCallback {
    fun startChatActivity(userName: String)
}