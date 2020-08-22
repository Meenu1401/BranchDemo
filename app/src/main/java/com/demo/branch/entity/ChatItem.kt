package com.demo.branch.entity

import com.google.gson.annotations.SerializedName

data class ChatItem(
    @SerializedName("body")
    val message: String,
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("timestamp")
    val timeStamp: String,
    @SerializedName("thread_id")
    val threadId: Int,
    var chatTime: Long
)