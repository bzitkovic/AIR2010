package com.escaper.escaper.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.circuitmessing.R
import com.example.core.IQuestion

class Preferences(context: Context) {
    private val prefsName: String = context.getString(R.string.prefs)
    private val prefs: SharedPreferences = context.getSharedPreferences(prefsName, 0)

    var isConnected: Boolean
        get() = prefs.getBoolean("isConnected", false)
        set(value) = prefs.edit().putBoolean("isConnected", value).apply()


    var nightMode: Boolean
        get() = prefs.getBoolean("night_mode", false)
        set(value) = prefs.edit().putBoolean("night_mode", value).apply()

    var username: String
        get() = prefs.getString("username", "noName").toString()
        set(value) = prefs.edit().putString("username", value).apply()
}