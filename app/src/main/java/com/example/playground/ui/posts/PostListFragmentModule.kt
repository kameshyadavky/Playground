package com.example.playground.ui.posts

import androidx.lifecycle.ViewModel
import com.example.playground.di.modules.NetworkModule
import com.example.playground.util.diUtil.ViewModelKey
import com.example.playground.util.diUtil.scope.FragmentScoped
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class PostListFragmentModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun postListFragment(): PostListFragment

    @Binds
    @IntoMap
    @ViewModelKey(PostListViewModel::class)
    abstract fun bindMainViewModel(viewModel: PostListViewModel): ViewModel
}