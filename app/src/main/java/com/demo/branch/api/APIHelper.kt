package com.demo.branch.api

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class APIHelper @Inject constructor(private val mAPIService: APIService) : NetworkAPIs {

    override fun getAPIService(): APIService {
        return mAPIService
    }
}
