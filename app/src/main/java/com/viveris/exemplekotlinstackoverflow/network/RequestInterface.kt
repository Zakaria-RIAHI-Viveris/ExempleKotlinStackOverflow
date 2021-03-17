package com.viveris.exemplekotlinstackoverflow.network

import com.viveris.exemplekotlinstackoverflow.model.Users
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface RequestInterface {

    @GET("users?order=desc&sort=reputation&site=stackoverflow")
    fun fetchUsers(): Call<Users>

    @GET("users?order=desc&sort=reputation&site=stackoverflow")
    suspend fun fetchCoroutineUsers(): Users

    @GET("users?order=desc&sort=reputation&site=stackoverflow")
    fun fetchRxUsers(): Single<Users>

}