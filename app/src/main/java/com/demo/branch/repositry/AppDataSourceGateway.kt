package com.demo.branch.repositry

import com.demo.branch.entity.ChatItem
import com.demo.branch.entity.ChatsList
import com.demo.branch.entity.ConversationItem
import com.demo.branch.entity.Response
import io.reactivex.Observable

interface AppDataSourceGateway {
    fun getChatList(
        authToken: String
    ): Observable<Response<ChatsList>>

    fun getThreadIdData(authToken: String, conversationItem: ConversationItem):Observable<Response<ChatsList>>

    fun getNewChatItem(authToken: String, conversationItem: ConversationItem):Observable<Response<ChatItem>>

}
