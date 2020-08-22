package com.demo.branch.user

import com.demo.branch.main.MainActivity
import dagger.Subcomponent

@LoggedUserScope
@Subcomponent
interface UserComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): UserComponent
    }
    fun inject(activity: MainActivity)
}
