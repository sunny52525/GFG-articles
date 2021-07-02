package com.gfg.gfgkotlinflow.network

import com.gfg.gfgkotlinflow.model.CommentModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/comments/{id}")
    suspend fun getComments(@Path("id") id: Int): CommentModel

}