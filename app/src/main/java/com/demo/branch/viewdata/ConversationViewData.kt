package com.demo.branch.viewdata

import com.demo.branch.entity.ChatItem
import com.demo.branch.user.UserManager
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import java.util.ArrayList
import javax.inject.Inject

class ConversationViewData @Inject constructor(private val userManager: UserManager) {

    private val conversationListObserver = PublishSubject.create<List<ChatItem>>()
    private val conversationItem = PublishSubject.create<ChatItem>()
    private val progressBarVisibility = BehaviorSubject.createDefault(true)
    private val failureMessageObserver = BehaviorSubject.create<String>()

    val failureMessage: Observable<String>
        get() = failureMessageObserver

    val newMessage: Observable<ChatItem>
        get() = conversationItem

    var chatItemsList = ArrayList<ChatItem>()
    val conversationList: Observable<List<ChatItem>>
        get() = conversationListObserver

    fun getAuthToken(): String = userManager.authToken

    val progressBarState: Observable<Boolean>
        get() = progressBarVisibility

    internal fun setConversationList(conversationList: List<ChatItem>) {
        this.chatItemsList.clear()
        this.chatItemsList.addAll(conversationList)
        conversationListObserver.onNext(chatItemsList)
    }


    internal fun setNewConversationItem(chatItem: ChatItem) =
        conversationItem.onNext(chatItem)

    internal fun setProgressBarVisibility(isVisible: Boolean) =
        progressBarVisibility.onNext(isVisible)


    internal fun showFailureMessage() {
        failureMessageObserver.onNext("Something went wrong. Please try again later!!")
    }

}