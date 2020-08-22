package com.demo.branch.di

import com.demo.branch.login.LoginComponent
import com.demo.branch.registration.RegistrationComponent
import com.demo.branch.user.UserComponent
import dagger.Module

@Module(
    subcomponents = [
        RegistrationComponent::class,
        LoginComponent::class,
        UserComponent::class
    ]
)
class AppSubcomponents
