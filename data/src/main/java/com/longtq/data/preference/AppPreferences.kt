package com.longtq.data.preference

import android.content.SharedPreferences
import javax.inject.Inject

class AppPreferences @Inject constructor(private val sharedPreferences: SharedPreferences) {
    fun isFirstLaunch(): Boolean {
        return sharedPreferences.getBoolean("isFirstLaunch", true)
    }

    fun setIsFirstLaunch(isFirstLaunch: Boolean) {
        sharedPreferences.edit().putBoolean("isFirstLaunch", isFirstLaunch).apply()
    }
}