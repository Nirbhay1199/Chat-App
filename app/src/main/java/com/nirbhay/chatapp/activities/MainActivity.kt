package com.nirbhay.chatapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nirbhay.chatapp.Constants.Companion.NAME
import com.nirbhay.chatapp.PreferenceManager
import com.nirbhay.chatapp.SocketHandler
import com.nirbhay.chatapp.adapter.AdapterCallback
import com.nirbhay.chatapp.adapter.UserListAdapter
import com.nirbhay.chatapp.databinding.ActivityMainBinding
import io.socket.client.Socket
import org.json.JSONArray

class MainActivity : AppCompatActivity(), AdapterCallback {
    private lateinit var binding: ActivityMainBinding
    lateinit var mSocket: Socket
    private lateinit var userList: ArrayList<Any>
    private lateinit var adapter: UserListAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userList = arrayListOf()
        SocketHandler.setSocket()
        SocketHandler.establishConnection()

        mSocket = SocketHandler.getSocket()

        linearLayoutManager = LinearLayoutManager(this)
        binding.userList.layoutManager = linearLayoutManager

        adapter = UserListAdapter(userList)
        binding.userList.adapter = adapter
        adapter.setAdapterCallback(this)

        mSocket.emit("register", PreferenceManager.getStringValue(NAME))


        mSocket.on("userList"){args ->
            val userNamesArray = args[0] as JSONArray

            for (i in 0 until userNamesArray.length()){
                val userName = userNamesArray.getString(i)
                runOnUiThread {
                    if (!userList.contains(userName)) {
                        userList.add(userName)
                        adapter.notifyDataSetChanged()
                    }
                    val mU = PreferenceManager.getStringValue(NAME)
                    if (userList.contains(mU.toString())){
                        userList.remove(mU.toString())
                        adapter.notifyDataSetChanged()

                    }
                }
            }


        }




    }

    override fun startChatActivity(userName: String) {
        val chatActivity = Intent(this, ChatActivity::class.java)
        chatActivity.putExtra("userName", userName)
        startActivity(chatActivity)
    }
}