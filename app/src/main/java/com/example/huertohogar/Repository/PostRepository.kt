package com.example.huertohogar.Repository

import com.example.huertohogar.model.Post


class PostRepository {
    suspend fun getPosts(): List<Post>{
        return RetrofitInstance.api.getPosts()
    }

}