package com.example.playground.injection.modules

import android.arch.lifecycle.ViewModelProvider
import com.example.playground.injection.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
internal abstract class ViewModelBuilder {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory):
            ViewModelProvider.Factory
}