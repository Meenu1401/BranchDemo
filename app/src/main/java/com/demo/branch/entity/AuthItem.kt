package com.demo.branch.entity

import com.google.gson.annotations.SerializedName

data class AuthItem(
    @SerializedName("auth_token")
    val authToken: String,

    @SerializedName("error")
    val error: String
)