package com.demo.branch.repositry

import com.demo.branch.api.NetworkAPIs
import com.demo.branch.entity.ChatItem
import com.demo.branch.entity.ChatsList
import com.demo.branch.entity.ConversationItem
import com.demo.branch.entity.Response
import com.demo.branch.extensions.createError
import com.demo.branch.extensions.createNewMessageError
import com.demo.branch.extensions.updateTimeStamp
import io.reactivex.Observable
import javax.inject.Inject

class AppRemoteDataSourceGatewayImpl @Inject constructor(private val mNetworkAPIs: NetworkAPIs) :
    AppDataSourceGateway {

    override fun getChatList(
        authToken: String
    ): Observable<Response<ChatsList>> {
        return mNetworkAPIs.getAPIService()
            .getChatList(authToken)
            .map {
                it.distinctBy { t -> t.threadId }
            }.map {
                if (!it.isNullOrEmpty()) {
                    Response.Success(ChatsList(updateTimeStamp(it).sortedByDescending { it.chatTime }))
                } else {
                    createError(Exception("data fetch failed"))
                }
            }
    }

    override fun getNewChatItem(
        authToken: String,
        conversationItem: ConversationItem
    ): Observable<Response<ChatItem>> {
        return mNetworkAPIs.getAPIService()
            .createNewMessage(authToken, conversationItem.threadId, conversationItem.message)
            .map {
                if (it != null) {
                    Response.Success(it)
                } else {
                    createNewMessageError(Exception("data fetch failed"))
                }
            }
    }

    override fun getThreadIdData(
        authToken: String,
        conversationItem: ConversationItem
    ): Observable<Response<ChatsList>> {
        return mNetworkAPIs.getAPIService()
            .getConversation(authToken, conversationItem.threadId, conversationItem.message)
            .map {
                it.filter { t -> t.threadId == conversationItem.threadId }
            }.map {
                if (!it.isNullOrEmpty()) {
                    Response.Success(ChatsList(updateTimeStamp(it).sortedBy { it.chatTime }))
                } else {
                    createError(Exception("data fetch failed"))
                }
            }
    }

}