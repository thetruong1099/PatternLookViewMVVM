package com.example.patternlookviewmvvm.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.patternlookviewmvvm.R
import com.example.patternlookviewmvvm.view.customView.patternLookScreenView.PatternViewState
import com.example.patternlookviewmvvm.viewModel.DataViewModel
import com.example.patternlookviewmvvm.viewModel.SharedPreferenceViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val sharedPreferenceViewModel by lazy {
        ViewModelProvider(
            this,
            SharedPreferenceViewModel.SharePreferenceViewModelFactory(this)
        )[SharedPreferenceViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        
        plv_login.setOnChangeStateListener { state ->
            when (state) {
                is PatternViewState.Started -> {
                    tv_error.text = ""
                }
                is PatternViewState.Success -> {
                    sharedPreferenceViewModel.getPassword().observe(this) { password ->
                        if (plv_login.getPassword() == password) {
                            showDialog()
                        } else {
                            plv_login.setErrorColor()
                        }
                    }
                }
                is PatternViewState.Error -> {
                    tv_error.text = "Wrong password"
                }
            }
        }
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Logged in successfully!!")
            .setPositiveButton("Yes") { dialogInterface, which -> }
        val alertDialog = builder.create()
        alertDialog.show()
    }
}