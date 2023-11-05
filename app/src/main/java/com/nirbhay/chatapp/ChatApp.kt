package com.nirbhay.chatapp

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatDelegate
import com.nirbhay.chatapp.Constants.Companion.IS_LOGIN
import com.nirbhay.chatapp.activities.MainActivity
import dagger.hilt.android.HiltAndroidApp

class ChatApp: Application() {
    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        PreferenceManager.init(this)


    }
}