package com.example.playground.di.modules

import com.example.playground.MainActivity
import com.example.playground.MainActivityModule
import com.example.playground.ui.posts.PostListFragmentModule
import com.example.playground.util.diUtil.scope.ActivityScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * We want Dagger.Android to create a Subcomponent which has a parent Component of whichever module
 * ActivityBindingModule is on, in our case that will be [AppComponent]. You never
 * need to tell [AppComponent] that it is going to have all these subcomponents
 * nor do you need to tell these subcomponents that [AppComponent] exists.
 * We are also telling Dagger.Android that this generated SubComponent needs to include the
 * specified modules and be aware of a scope annotation [@ActivityScoped].
 * When Dagger.Android annotation processor runs it will create 2 subcomponents for us.
 */

@Module
abstract class ActivityBindingModule {
    /**
     * example code
     *  @ActivityScoped
     *  @ContributesAndroidInjector(modules = [])
     *  internal abstract fun launcherActivity(): LauncherActivity
     *  only activities will be listed here
     */

    /**
     * Creates injector for PostListActivity
     */
    @ActivityScoped
    @ContributesAndroidInjector(modules = [MainActivityModule::class
        ,PostListFragmentModule::class])
    internal abstract fun mainActivity(): MainActivity



}