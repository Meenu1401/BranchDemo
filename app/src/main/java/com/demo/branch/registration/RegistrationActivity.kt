package com.demo.branch.registration

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demo.branch.MyApplication
import com.demo.branch.R
import com.demo.branch.chat.ChatThreadActivity
import com.demo.branch.registration.enterdetails.EnterDetailsFragment
import com.demo.branch.viewdata.RegistrationViewData
import javax.inject.Inject

class RegistrationActivity : AppCompatActivity() {

    lateinit var registrationComponent: RegistrationComponent

    @Inject
    lateinit var registrationViewData: RegistrationViewData


    override fun onCreate(savedInstanceState: Bundle?) {
        registrationComponent = (application as MyApplication).appComponent
            .registrationComponent().create()

        registrationComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_holder, EnterDetailsFragment())
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    fun onDetailsEntered() {
        registrationViewData.registerUser()
        startActivity(Intent(this, ChatThreadActivity::class.java))
        finish()
    }
}
