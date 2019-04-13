package com.example.playground.data.network

import androidx.paging.DataSource
import androidx.room.*
import com.example.playground.data.model.Post
import io.reactivex.Observable

@Dao
interface RoomApi {
    @Query("Select * from post")
    fun getAll() : Observable<List<Post>>

    @Query("Select * from post")
    fun getAllPosts(): DataSource.Factory<Int,Post>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg post: Post)

    @Delete
    fun delete(post: Post)
}

