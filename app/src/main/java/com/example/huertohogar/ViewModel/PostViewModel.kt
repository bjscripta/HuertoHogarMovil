package com.example.huertohogar.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.huertohogar.Repository.PostRepository
import com.example.huertohogar.model.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostViewModel: ViewModel() {
    private val repository = PostRepository()
    private val _postList = MutableStateFlow<List<Post>>(emptyList())
    val postList: StateFlow<List<Post>> = _postList
    init {
        fetchPost()
    }

    private fun fetchPost(){
        viewModelScope.launch {
            try {
                _postList.value = repository.getPosts()
            }  catch (e: Exception){
                println("Error, en obtener datos: ${e.localizedMessage}")
            }
        }
    }
}