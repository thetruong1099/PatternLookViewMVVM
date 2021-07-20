package com.example.patternlookviewmvvm.repository

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SharePreferenceRepository(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("myData", Context.MODE_PRIVATE)
    private val sharedPreferencesLiveData = SharePreferenceLiveData(sharedPreferences, "myPassword")
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    suspend fun setPassword(password: String) {
        withContext(Dispatchers.IO) {
            editor.putString("myPassword", password)
            editor.commit()
        }
    }

    fun getPassword():SharePreferenceLiveData  = sharedPreferencesLiveData
}