package com.demo.branch.viewdata

import com.demo.branch.entity.AuthItem
import com.demo.branch.login.LoginError
import com.demo.branch.login.LoginSuccess
import com.demo.branch.login.LoginViewState
import com.demo.branch.user.UserManager
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class LoginViewData @Inject constructor(private val userManager: UserManager) {

    private val _progressBarState = BehaviorSubject.create<Boolean>()
    val progressBarState: Observable<Boolean>
        get() = _progressBarState

    private val _loginState = BehaviorSubject.create<LoginViewState>()
    val loginState: Observable<LoginViewState>
        get() = _loginState


    private fun setLoginState(loginViewState: LoginViewState) {
        _loginState.onNext(loginViewState)
    }


    fun login(authItem: AuthItem) {
        if (authItem.authToken.isNotEmpty()) {
            userManager.registerUser(authItem.authToken)
            setLoginState(LoginSuccess)
        } else {
            setLoginState(LoginError)
        }
    }


    internal fun setProgressBarVisibility(isVisible: Boolean) =
        _progressBarState.onNext(isVisible)


}