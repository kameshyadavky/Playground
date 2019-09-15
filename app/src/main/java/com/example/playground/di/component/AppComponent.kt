package com.example.playground.di.component

import com.example.playground.ModernApplication
import com.example.playground.di.modules.*
import com.example.playground.util.diUtil.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * This is the main part of Dagger, all the module classes provided here
 * AppComponent has the scope of whole application
 * Any module that is mentioned here will be provided to whole activity
 */
@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        RoomDatabaseModule::class,
        FirebaseModule::class,
        ActivityBindingModule::class])
interface AppComponent : AndroidInjector<ModernApplication> {

    @Component.Factory
    abstract class Builder : AndroidInjector.Factory<ModernApplication>
}