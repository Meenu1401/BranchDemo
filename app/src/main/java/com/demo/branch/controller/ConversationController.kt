package com.demo.branch.controller

import com.demo.branch.entity.ConversationItem
import com.demo.branch.interactor.FetchConversationInteractor
import com.demo.branch.interactor.PostMessageInteractor
import com.demo.branch.presenter.ConversationPresenter
import com.demo.branch.viewdata.ConversationViewData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ConversationController @Inject constructor(
    private val fetchConverstaionInteractor: FetchConversationInteractor,
    private val postMessageInteractor: PostMessageInteractor,
    private val chatPresenter: ConversationPresenter
) {

    fun getViewData(): ConversationViewData {
        return chatPresenter.getViewData()
    }


    fun fetchConversations(conversationItem: ConversationItem): Disposable {
        return fetchConverstaionInteractor.fetch(getViewData().getAuthToken(), conversationItem)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                chatPresenter.handleResponse(it)
            }
    }

    fun postMessage(conversationItem: ConversationItem): Disposable {
        return postMessageInteractor.post(getViewData().getAuthToken(), conversationItem)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                chatPresenter.handleFailure()
            }
            .subscribe {
                chatPresenter.handleChatItemResponse(it)
            }
    }

}