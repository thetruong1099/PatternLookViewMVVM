package com.example.patternlookviewmvvm.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.patternlookviewmvvm.R
import com.example.patternlookviewmvvm.view.customView.patternLookScreenView.PatternViewState
import com.example.patternlookviewmvvm.viewModel.DataViewModel
import com.example.patternlookviewmvvm.viewModel.SharedPreferenceViewModel
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private val sharedPreferenceViewModel by lazy {
        ViewModelProvider(
            this,
            SharedPreferenceViewModel.SharePreferenceViewModelFactory(this)
        )[SharedPreferenceViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        plv_sing_up.setOnChangeStateListener { state ->
            when (state) {
                is PatternViewState.Success -> {
                    val password = plv_sing_up.getPassword()
                    sharedPreferenceViewModel.setPassWord(password)
                    startMainActivity()
                }
                is PatternViewState.Error -> {
                    tv_error.text = "Connect at least 4 dot! Try Again "
                }
            }
        }

    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        finish()
    }
}