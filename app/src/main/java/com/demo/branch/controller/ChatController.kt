package com.demo.branch.controller

import com.demo.branch.entity.ConversationItem
import com.demo.branch.interactor.FetchChatsInteractor
import com.demo.branch.interactor.FetchConversationInteractor
import com.demo.branch.presenter.ChatPresenter
import com.demo.branch.viewdata.ChatViewData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ChatController @Inject constructor(
    private val fetchChatsInteractor: FetchChatsInteractor,
    private val fetchConverstaionInteractor: FetchConversationInteractor,
    private val chatPresenter: ChatPresenter
) {

    fun getViewData(): ChatViewData {
        return chatPresenter.getViewData()
    }

    fun fetchChats(): Disposable {
        return fetchChatsInteractor.fetch(getViewData().getAuthToken())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe {
                chatPresenter.handleResponse(it)
            }
    }

    fun fetchConversations(conversationItem: ConversationItem): Disposable {
        return fetchConverstaionInteractor.fetch(getViewData().getAuthToken(), conversationItem)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                chatPresenter.handleResponse(it)
            }
    }

}