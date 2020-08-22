package com.demo.branch.interactor

import com.demo.branch.entity.Response
import com.demo.branch.entity.ChatsList
import com.demo.branch.repositry.AppDataSourceGateway
import io.reactivex.Observable
import javax.inject.Inject

class FetchChatsInteractor @Inject constructor(private val appDataSourceGateway: AppDataSourceGateway) {

    fun fetch(authToken: String): Observable<Response<ChatsList>> {
        return appDataSourceGateway.getChatList(authToken)
    }

}