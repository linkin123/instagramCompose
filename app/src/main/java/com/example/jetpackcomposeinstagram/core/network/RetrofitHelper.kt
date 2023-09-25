package com.example.jetpackcomposeinstagram.core.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https:https://designer.mocky.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}