package com.example.social_goal_sharing.ui.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreference {
    private val fileName: String="SHARED_FILENAME"

    fun setAccessToken(context: Context, accessToken :String){
       val preferences:SharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        preferences.edit().putString("accessToken",accessToken).apply()
    }
}