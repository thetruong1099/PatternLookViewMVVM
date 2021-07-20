package com.example.patternlookviewmvvm.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.patternlookviewmvvm.repository.SharePreferenceLiveData
import com.example.patternlookviewmvvm.repository.SharePreferenceRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.IllegalArgumentException

class SharedPreferenceViewModel(context: Context) : ViewModel() {
    private val sharePreferenceRepository: SharePreferenceRepository =
        SharePreferenceRepository(context)

    fun setPassWord(password: String) =
        viewModelScope.launch { sharePreferenceRepository.setPassword(password) }

    fun getPassword(): SharePreferenceLiveData = sharePreferenceRepository.getPassword()

    class SharePreferenceViewModelFactory(private val context: Context) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SharedPreferenceViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SharedPreferenceViewModel(context) as T
            }
            throw IllegalArgumentException("unable construct viewModel")
        }

    }
}