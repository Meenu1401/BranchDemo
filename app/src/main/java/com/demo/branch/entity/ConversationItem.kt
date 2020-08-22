package com.demo.branch.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ConversationItem(
    val threadId: Int,
    val message: String
) : Parcelable