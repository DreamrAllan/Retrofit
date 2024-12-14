package com.example.konekinapiapp.network

import com.example.konekinapiapp.model.Authors
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("authors")
    fun getAllAuthors(): Call<List<Authors>>
}