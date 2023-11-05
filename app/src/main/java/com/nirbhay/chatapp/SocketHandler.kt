package com.nirbhay.chatapp

import android.util.Log
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

object SocketHandler {

    lateinit var mSocket: Socket

    @Synchronized
    fun setSocket() {
        try {
//            mSocket = IO.socket("http://192.168.29.135:3000")
            mSocket = IO.socket("https://chat-app-26n3.onrender.com")
            Log.d("LLLLLL","success")
        } catch (e: URISyntaxException) {
            Log.d("LLLLLL","failure")
            e.printStackTrace()
        }
    }

    @Synchronized
    fun getSocket(): Socket {
        return mSocket
    }

    @Synchronized
    fun establishConnection() {
        mSocket.connect()
    }

    @Synchronized
    fun closeConnection() {
        mSocket.disconnect()
    }
}