package com.demo.branch.presenter

import com.demo.branch.viewdata.ChatViewData
import com.demo.branch.entity.ChatsList
import com.demo.branch.entity.Response
import javax.inject.Inject

class ChatPresenter @Inject constructor(private val viewData: ChatViewData) {


    fun handleResponse(it: Response<ChatsList>) {
        viewData.setProgressBarVisibility(false)
        if (it.isSuccessful) {
            handleSuccess(it.data!!)
        } else {
            handleFailure()
        }
    }

    private fun handleFailure() {
        TODO("Not yet implemented")
    }

    private fun handleSuccess(data: ChatsList) {
        viewData.setChatsList(data.list)
    }

    fun getViewData(): ChatViewData {
        return viewData
    }

}