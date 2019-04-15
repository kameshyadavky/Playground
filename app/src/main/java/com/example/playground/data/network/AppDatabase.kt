package com.example.playground.data.network

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.playground.data.model.Post
import com.example.playground.data.network.RoomApi

@Database(entities = [Post::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): RoomApi
}