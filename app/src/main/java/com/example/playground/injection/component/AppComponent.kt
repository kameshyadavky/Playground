package com.example.playground.injection.component

import com.example.playground.ModernApplication
import com.example.playground.injection.modules.AppModule
import com.example.playground.injection.modules.MainActivityModule
import com.example.playground.injection.modules.ViewModelBuilder
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
        ViewModelBuilder::class,
        MainActivityModule::class])
interface AppComponent : AndroidInjector<ModernApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<ModernApplication>()
}