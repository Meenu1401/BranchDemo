package com.demo.branch.api

import com.demo.branch.entity.AuthItem
import com.demo.branch.entity.ChatItem
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query


interface APIService {
    @GET("/api/messages")
    fun getChatList(@Header("X-Branch-Auth-Token") authToken: String): Observable<List<ChatItem>>


    @POST("/api/login")
    fun loginUser(
        @Query("username") username: String,
        @Query("password") password: String
    ): Observable<AuthItem>


    @GET("/api/messages")
    fun getConversation(
        @Header("X-Branch-Auth-Token") authToken: String,
        @Query("thread_id") id: Int,
        @Query("body") messaged: String
    ): Observable<List<ChatItem>>

    @POST("/api/messages")
    fun createNewMessage(
        @Header("X-Branch-Auth-Token") authToken: String,
        @Query("thread_id") id: Int,
        @Query("body") messaged: String
    ): Observable<ChatItem>


}
