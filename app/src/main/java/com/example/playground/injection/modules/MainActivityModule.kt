package com.example.playground.injection.modules

import android.arch.lifecycle.ViewModel
import com.example.playground.ui.PostListActivity
import com.example.playground.ui.PostListViewModel
import com.example.playground.util.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class MainActivityModule {


    @ContributesAndroidInjector()
    internal abstract fun postListActivity(): PostListActivity

    @Binds
    @IntoMap
    @ViewModelKey(PostListViewModel::class)
    abstract fun bindMainViewModel(viewModel: PostListViewModel): ViewModel
}