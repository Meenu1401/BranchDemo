package com.demo.branch.registration

import com.demo.branch.di.ActivityScope
import com.demo.branch.login.LoginActivity
import com.demo.branch.registration.enterdetails.EnterDetailsFragment
import dagger.Subcomponent
@ActivityScope
@Subcomponent
interface RegistrationComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): RegistrationComponent
    }
    fun inject(activity: RegistrationActivity)
    fun inject(fragment: EnterDetailsFragment)
    fun inject(loginActivity: LoginActivity)
}
