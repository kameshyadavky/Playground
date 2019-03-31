package com.example.playground.di.modules

import android.content.Context
import com.example.playground.ModernApplication
import dagger.Module
import dagger.Provides

/**
 * Module for providing Application context
 *
 */
@Module
class AppModule{

    @Provides
    fun providesContext(application: ModernApplication): Context {
        return application.applicationContext
    }

}