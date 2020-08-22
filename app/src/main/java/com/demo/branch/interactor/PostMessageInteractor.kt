package com.demo.branch.interactor

import com.demo.branch.entity.ChatItem
import com.demo.branch.entity.ConversationItem
import com.demo.branch.entity.Response
import com.demo.branch.repositry.AppDataSourceGateway
import io.reactivex.Observable
import javax.inject.Inject

class PostMessageInteractor @Inject constructor(private val appDataSourceGateway: AppDataSourceGateway) {

    fun post(
        authToken: String,
        conversationItem: ConversationItem
    ): Observable<Response<ChatItem>> {
        return appDataSourceGateway.getNewChatItem(authToken, conversationItem)
    }

}