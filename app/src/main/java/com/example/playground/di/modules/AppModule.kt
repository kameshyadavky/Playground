package com.example.playground.di.modules

import android.content.Context
import com.example.playground.ModernApplication
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Module for providing Application context
 *
 */
@Module
class AppModule{

    @Named("AppContext")
    @Provides
    fun providesContext(application: ModernApplication): Context {
        return application.applicationContext
    }

}