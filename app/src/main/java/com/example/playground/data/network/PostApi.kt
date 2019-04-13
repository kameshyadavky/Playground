package com.example.playground.data.network

import com.example.playground.data.model.Post
import io.reactivex.Observable
import retrofit2.http.GET
import javax.sql.DataSource

/**
 * The interface which provides methods to get result of webservices
 */
interface PostApi {
    /**
     * Get the list of the pots from the API
     */
    @GET("/posts")
    fun getPosts(): Observable<List<Post>>

    @GET("/posts")
    fun getPagedPost(): androidx.paging.DataSource.Factory<Int, Post>
}
