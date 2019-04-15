package com.example.playground.ui.navigation

import com.example.playground.util.diUtil.scope.FragmentScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BottomNavigationFragmentModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun bindBottomNavigationFragment(): BottomNavigationFragment
}