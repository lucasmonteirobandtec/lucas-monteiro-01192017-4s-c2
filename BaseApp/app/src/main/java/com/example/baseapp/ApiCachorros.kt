package com.example.baseapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiCachorros {

    @POST("cachorros")
    fun save(@Body dog: Dog): Call<Dog>

    @GET("cachorros")
    fun list(): Call<List<Dog>>

}