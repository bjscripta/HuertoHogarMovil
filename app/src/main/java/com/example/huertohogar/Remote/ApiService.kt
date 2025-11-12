package com.example.huertohogar.Remote
import com.example.huertohogar.model.Post
import  retrofit2.http.GET

interface ApiService {

    @GET("/posts")
    suspend fun getPosts(): List<Post>
}