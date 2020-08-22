package com.demo.branch.di

import android.content.Context
import com.demo.branch.api.NetworkModule
import com.demo.branch.chat.ChatComponent
import com.demo.branch.conversation.ConversationComponent
import com.demo.branch.login.LoginComponent
import com.demo.branch.registration.RegistrationComponent
import com.demo.branch.user.UserManager
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton
@Singleton
@Component(modules = [StorageModule::class, AppSubcomponents::class, ApiModule::class,NetworkModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
    fun registrationComponent(): RegistrationComponent.Factory
    fun loginComponent(): LoginComponent.Factory
    fun userManager(): UserManager
    fun chatComponent(): ChatComponent.Factory
    fun conversationComponent(): ConversationComponent.Factory
}
