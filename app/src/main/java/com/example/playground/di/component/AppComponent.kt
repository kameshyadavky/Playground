package com.example.playground.di.component

import com.example.playground.ModernApplication
import com.example.playground.di.modules.AppModule
import com.example.playground.di.modules.ActivityBindingModule
import com.example.playground.util.diUtil.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * This is the main part of Dagger, all the module classes provided here
 */
@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AppModule::class,
        ViewModelModule::class,
        ActivityBindingModule::class])
interface AppComponent : AndroidInjector<ModernApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<ModernApplication>()
}