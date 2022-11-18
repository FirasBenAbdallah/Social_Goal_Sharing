package com.example.social_goal_sharing.ui.base

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/login")
    fun meth1(@Body map: HashMap<String, String>): Call<Login>
    companion object {
        var BASE_URL = "http://192.168.1.12:3000"
        fun create() : ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}