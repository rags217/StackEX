package com.raghu.stackex.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface WebService {

    @GET
    suspend fun getUser(@Url url: String): Response<ResponseBody>
}