package com.demo.branch.chat

import com.demo.branch.di.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface ChatComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ChatComponent
    }

    fun inject(activity: ChatThreadActivity)
}

