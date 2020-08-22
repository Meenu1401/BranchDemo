package com.demo.branch.controller

import com.demo.branch.interactor.LoginUserInteractor
import com.demo.branch.presenter.LoginPresenter
import com.demo.branch.viewdata.LoginViewData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginController @Inject constructor(
    private val loginInteractor: LoginUserInteractor,
    private val loginPresenter: LoginPresenter
) {

    fun getViewData(): LoginViewData {
        return loginPresenter.getViewData()
    }

    fun login(username: String, password: String): Disposable {
        loginPresenter.setProgressBarVisibility(true)
        return loginInteractor.login(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    loginPresenter.handleResponse(it)
                },
                {
                    loginPresenter.handleError(it)
                }
            )
    }

}