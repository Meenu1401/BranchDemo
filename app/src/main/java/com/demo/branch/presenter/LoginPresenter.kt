package com.demo.branch.presenter

import com.demo.branch.entity.AuthItem
import com.demo.branch.entity.Response
import com.demo.branch.viewdata.LoginViewData
import javax.inject.Inject

class LoginPresenter @Inject constructor(private val viewData: LoginViewData) {
    fun handleResponse(it: Response<AuthItem>) {
        setProgressBarVisibility(false)
        if (it.isSuccessful) {
            viewData.login(it.data!!)
        }
    }

    fun handleError(it: Throwable) {
        setProgressBarVisibility(false)
        viewData.login(AuthItem("", it.message!!))
    }

    fun getViewData(): LoginViewData {
        return viewData
    }

    fun setProgressBarVisibility(isVisible: Boolean) {
        viewData.setProgressBarVisibility(isVisible)
    }

}