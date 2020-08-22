package com.demo.branch.extensions

import com.demo.branch.entity.ChatItem
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun changeDateFormat(date: String): String? {
    val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val output = SimpleDateFormat(
        "dd/MM/yyyy hh:mm a"
    )

    var d: Date? = null
    try {
        d = input.parse(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return if (d != null)
        output.format(d)
    else
        null
}

 fun updateTimeStamp(list: List<ChatItem>): List<ChatItem> {
    val sdf =
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)

    for (chatItem in list) {
        try {
            val date = sdf.parse(chatItem.timeStamp) as Date
            chatItem.chatTime = date.time
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    return list
}
