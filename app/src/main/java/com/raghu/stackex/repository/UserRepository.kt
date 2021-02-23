package com.raghu.stackex.repository

import com.google.gson.GsonBuilder
import com.raghu.stackex.models.UserResponse
import com.raghu.stackex.network.WebService
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserRepository  {

    suspend fun getUsers(searchString: String) : Response<ResponseBody> {
        val httpClientBuilder = OkHttpClient.Builder()

        var client = httpClientBuilder.build()

        webService = Retrofit.Builder()
            .baseUrl(SERVER_END_POINT)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(WebService::class.java)
        return webService.getUser(SERVER_END_POINT.plus(GET_USER_WITH_FILTERS).plus(searchString))
    }

    companion object {
        private val gson = GsonBuilder()
            .setLenient()
            .create()

        private lateinit var webService: WebService
        private const val SERVER_END_POINT = "https://api.stackexchange.com/"
        private const val GET_USER_WITH_FILTERS = "/users?page=1&pagesize=20&order=asc&sort=name&site=stackoverflow&inname="
    }
}