package com.demo.branch.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.demo.branch.BaseActivity
import com.demo.branch.MyApplication
import com.demo.branch.R
import com.demo.branch.chat.ChatThreadActivity
import com.demo.branch.controller.LoginController
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : BaseActivity(), View.OnClickListener {

    @Inject
    lateinit var loginController: LoginController


    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.loginComponent().create().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupViews()
        observeLoginState()
        observeProgressBarState()
    }

    private fun observeProgressBarState() {
        loginController.getViewData().progressBarState.subscribe {
            if (it)
                progressBar.visibility = View.VISIBLE
            else
                progressBar.visibility = View.GONE
        }.disposedBy(compositeDisposable)
    }

    private fun observeLoginState() {
        loginController.getViewData().loginState.subscribe {
            when (it) {
                is LoginSuccess -> {
                    startActivity(Intent(this, ChatThreadActivity::class.java))
                    finish()
                }
                is LoginError -> error.visibility = View.VISIBLE
            }
        }.disposedBy(compositeDisposable)
    }

    private fun setupViews() {
        username.doOnTextChanged { _, _, _, _ -> error.visibility = View.INVISIBLE }
        password.doOnTextChanged { _, _, _, _ -> error.visibility = View.INVISIBLE }
        login.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.login -> {
                if (validateCredentials())
                    loginController.login(
                        username.text.trim().toString(),
                        password.text.trim().toString()
                    )
                else
                    Toast.makeText(
                        this,
                        "Invalid username or password.",
                        Toast.LENGTH_SHORT
                    ).show()
            }
        }
    }

    private fun validateCredentials(): Boolean {
        return !TextUtils.isEmpty(username.text.trim().toString()) && !TextUtils.isEmpty(
            password.text.trim().toString()
        )
    }
}


