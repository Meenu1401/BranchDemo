package com.demo.branch.presenter

import com.demo.branch.entity.ChatItem
import com.demo.branch.entity.ChatsList
import com.demo.branch.entity.Response
import com.demo.branch.viewdata.ConversationViewData
import javax.inject.Inject

class ConversationPresenter @Inject constructor(private val viewData: ConversationViewData) {


    fun handleChatItemResponse(it: Response<ChatItem>) {
        viewData.setProgressBarVisibility(false)
        if (it.isSuccessful) {
            handleChatItemSuccess(it.data!!)
        } else {
            handleFailure()
        }
    }

    fun handleResponse(it: Response<ChatsList>) {
        viewData.setProgressBarVisibility(false)
        if (it.isSuccessful) {
            handleSuccess(it.data!!)
        } else {
            handleFailure()
        }
    }

    private fun handleSuccess(data: ChatsList) {
        viewData.setConversationList(data.list)
    }

    fun handleFailure() {
        viewData.showFailureMessage()
    }

    private fun handleChatItemSuccess(data: ChatItem) {
        viewData.setNewConversationItem(data)
    }

    fun getViewData(): ConversationViewData {
        return viewData
    }

}