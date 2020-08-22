package com.demo.branch.interactor

import com.demo.branch.entity.ChatsList
import com.demo.branch.entity.ConversationItem
import com.demo.branch.entity.Response
import com.demo.branch.repositry.AppDataSourceGateway
import io.reactivex.Observable
import javax.inject.Inject

class FetchConversationInteractor @Inject constructor(private val appDataSourceGateway: AppDataSourceGateway) {

    fun fetch(
        authToken: String,
        conversationItem: ConversationItem
    ): Observable<Response<ChatsList>> {
        return appDataSourceGateway.getThreadIdData(authToken, conversationItem)
    }

}