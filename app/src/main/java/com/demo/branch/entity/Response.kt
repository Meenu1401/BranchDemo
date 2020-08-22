package com.demo.branch.entity


sealed class Response<T>(val isSuccessful: Boolean, val data: T?, val exception: Throwable?) {
    class Success<T>(data: T) : Response<T>(true, data, null)
    class Failure<T>(exception: Throwable) : Response<T>(false, null, exception)
}