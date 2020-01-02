package com.example.shared.di

import androidx.work.Configuration
import com.example.shared.data.work.ChildWorkerFactory
import com.example.shared.data.work.PlaygroundWorkerFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * This module will provide all the dependency of shared module
 */
@Module
class SharedModule {

    @Singleton
    @Provides
    fun provideWorkManagerConfiguration(
        playgroundWorkerFactory: PlaygroundWorkerFactory
    ): Configuration {
        return Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.INFO)
            .setWorkerFactory(playgroundWorkerFactory)
            .build()
    }
}