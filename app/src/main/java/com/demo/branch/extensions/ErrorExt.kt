package com.demo.branch.extensions

import com.demo.branch.entity.AuthItem
import com.demo.branch.entity.ChatItem
import com.demo.branch.entity.ChatsList
import com.demo.branch.entity.Response

fun createError(exception: Exception): Response<ChatsList> =
    Response.Failure(exception)


fun createAuthError(exception: Exception): Response<AuthItem> =
    Response.Failure(exception)


fun createNewMessageError(exception: Exception): Response<ChatItem> =
    Response.Failure(exception)