package com.demo.branch.conversation

import com.demo.branch.di.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface ConversationComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ConversationComponent
    }

    fun inject(activity: ConversationActivity)
}

