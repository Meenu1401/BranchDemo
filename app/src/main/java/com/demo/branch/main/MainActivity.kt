package com.demo.branch.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demo.branch.MyApplication
import com.demo.branch.R
import com.demo.branch.chat.ChatThreadActivity
import com.demo.branch.login.LoginActivity
import com.demo.branch.viewdata.MainViewData
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mainViewData: MainViewData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val userManager = (application as MyApplication).appComponent.userManager()
        if (!userManager.isUserRegistered()) {
            startActivity(Intent(this, LoginActivity::class.java))
        } else {
            startActivity(Intent(this, ChatThreadActivity::class.java))
        }
        finish()
    }
}
