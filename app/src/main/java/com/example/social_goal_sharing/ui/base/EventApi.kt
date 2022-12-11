package com.example.social_goal_sharing.ui.base

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EventApi {

    @GET("/getevents")
    fun getEvent(@Body map: HashMap<String, String>): Call<Void>
    @POST("/addevent")
    fun executeEventAdd(@Body map: HashMap<String, String>): Call<Void>

    companion object {
        var BASE_URL = "http://192.168.31.126:3001/event/"
        fun create() : EventApi {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(EventApi::class.java)
        }
    }
}