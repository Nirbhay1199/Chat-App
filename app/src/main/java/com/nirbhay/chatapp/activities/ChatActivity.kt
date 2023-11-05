package com.nirbhay.chatapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.nirbhay.chatapp.SocketHandler
import com.nirbhay.chatapp.adapter.ChatListAdapter
import com.nirbhay.chatapp.databinding.ActivityChatBinding
import io.socket.client.Socket
import org.json.JSONObject

class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    private lateinit var mSocket: Socket
    private lateinit var chatList: ArrayList<ChatMessage>
    private lateinit var chatAdapter: ChatListAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        chatList = arrayListOf()
        mSocket = SocketHandler.getSocket()

        linearLayoutManager = LinearLayoutManager(this)
        binding.chats.layoutManager = linearLayoutManager
        chatAdapter = ChatListAdapter(this, mSocket.id(),chatList)
        binding.chats.adapter = chatAdapter

        val userName = intent.getStringExtra("userName")
        binding.otherUserName.text = userName

        binding.otherUserName.setOnClickListener {
            finish()
        }

        binding.send.setOnClickListener {
            if (binding.message.text.toString().isEmpty()){
                Toast.makeText(this, "Cannot send empty message !", Toast.LENGTH_SHORT).show()
            }else {
                val messageData = JSONObject().apply {
                    put("id", mSocket.id())
                    put("message", binding.message.text.toString())
                }
                mSocket.emit("chat message", messageData)
                binding.message.text.clear()
            }
        }

        mSocket.on("chat message"){args ->
            runOnUiThread {
                val data = args[0] as JSONObject
                val socketId = data.getString("id")
                val message = data.getString("message")
                val chatMessage = ChatMessage(socketId, message)

                chatList.add(chatMessage)
                chatAdapter.notifyItemInserted(chatList.size - 1)
            }
        }

    }
}

data class ChatMessage(
    val id: String,
    val message: String
)