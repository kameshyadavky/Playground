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

    // @Named : if the two provides method are returning same type we have to use this scope
    // @use  { @Named("AppContext") context : Context }
    @Named("AppContext")
    @Provides
    fun providesContext(application: ModernApplication): Context {
        return application.applicationContext
    }

}