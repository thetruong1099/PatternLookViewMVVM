package com.example.patternlookviewmvvm.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataViewModel:ViewModel() {
    private val passwordLiveData = MutableLiveData<String>()

    fun setPassword(password:String){
        passwordLiveData.value = password
    }

    fun getPassword():MutableLiveData<String> = passwordLiveData
}