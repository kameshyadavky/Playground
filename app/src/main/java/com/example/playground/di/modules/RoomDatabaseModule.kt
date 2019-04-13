package com.example.playground.di.modules

import android.content.Context
import androidx.room.Room
import com.example.playground.data.network.AppDatabase
import com.example.playground.data.network.RoomApi
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class RoomDatabaseModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@Named("AppContext") context: Context): RoomApi {
        return Room.databaseBuilder(context, AppDatabase::class.java, "playground")
            .build().postDao()
    }

}