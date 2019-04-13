package com.example.playground

import com.example.playground.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class ModernApplication : DaggerApplication() {


    /**
     * AndroidInjector to inject this DaggerComponent to App
     */
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }

}