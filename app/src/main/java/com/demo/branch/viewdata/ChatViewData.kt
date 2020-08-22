package com.demo.branch.viewdata

import com.demo.branch.entity.ChatItem
import com.demo.branch.user.UserManager
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class ChatViewData @Inject constructor(private val userManager: UserManager) {

    private val chatListObserver = PublishSubject.create<List<ChatItem>>()
    val chatList: Observable<List<ChatItem>>
        get() = chatListObserver

    private val progressBarVisibility = BehaviorSubject.createDefault(true)

    fun observeProgressBarVisibility(): Observable<Boolean> =
        progressBarVisibility

    fun setChatsList(list: List<ChatItem>) =
        chatListObserver.onNext(list)

    internal fun setProgressBarVisibility(isVisible: Boolean) =
        progressBarVisibility.onNext(isVisible)


    fun getAuthToken(): String = userManager.authToken

}