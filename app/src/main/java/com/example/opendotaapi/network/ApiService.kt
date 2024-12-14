package com.example.opendotaapi.network

import com.example.opendotaapi.model.Hero
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("heroStats")
    fun getHeroStats(): Call<List<Hero>>
}
