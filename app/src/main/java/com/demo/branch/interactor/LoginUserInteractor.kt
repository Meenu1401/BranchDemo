package com.demo.branch.interactor

import com.demo.branch.api.NetworkAPIs
import com.demo.branch.entity.AuthItem
import com.demo.branch.entity.Response
import com.demo.branch.extensions.createAuthError
import io.reactivex.Observable
import javax.inject.Inject

class LoginUserInteractor @Inject constructor(
    private val mNetworkAPIs: NetworkAPIs
) {

    fun login(username: String, password: String): Observable<Response<AuthItem>> {
        return mNetworkAPIs.getAPIService()
            .loginUser(username, password)
            .map {
                if (it.authToken.isNotEmpty()) {
                    Response.Success(it)
                } else {
                    createAuthError(Exception(it.error))
                }
            }
    }
}