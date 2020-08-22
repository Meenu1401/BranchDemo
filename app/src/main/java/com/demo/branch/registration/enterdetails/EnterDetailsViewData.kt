package com.demo.branch.registration.enterdetails

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

private const val MAX_LENGTH = 5

class EnterDetailsViewData @Inject constructor() {

    private val _enterDetailsState = BehaviorSubject.create<EnterDetailsViewState>()
    val enterDetailsState: Observable<EnterDetailsViewState>
        get() = _enterDetailsState

    private fun setDetailState(enterDetailsViewState: EnterDetailsViewState) {
        _enterDetailsState.onNext(enterDetailsViewState)
    }

    fun validateInput(username: String, password: String) {
        when {
            username.length < MAX_LENGTH -> setDetailState(EnterDetailsError("Username has to be longer than 4 characters"))

            password.length < MAX_LENGTH ->
                setDetailState(EnterDetailsError("Password has to be longer than 4 characters"))
            else -> setDetailState(EnterDetailsSuccess)
        }
    }
}
