package com.demo.branch.login

import com.demo.branch.di.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [LoginActivityModule::class])
interface LoginComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): LoginComponent
    }

    fun inject(activity: LoginActivity)
}

