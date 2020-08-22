package com.demo.branch.login

sealed class LoginViewState
object LoginSuccess : LoginViewState()
object LoginError : LoginViewState()