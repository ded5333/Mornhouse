package com.example.mornhouse.retrofitmodel

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiRandomPoint {
    @GET("{point}")
    fun getFact(
        @Path("point")
        point: String,
    ): Call<String>
}