package com.example.playground.ui.posts

import androidx.lifecycle.ViewModel
import com.example.playground.util.diUtil.ViewModelKey
import com.example.playground.util.diUtil.scope.FragmentScoped
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class PostListFragmentModule {

    /**
     * Generates injector for PostListFragment scoped with Fragment
     */
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun postListFragment(): PostListFragment

    /**
     * Generates (key, value) pair for ViewModel
     * key : VM::class
     * value : viewModel  ...it is an instance of ViewModel
     */
    @Binds
    @IntoMap
    @ViewModelKey(PostListViewModel::class)
    abstract fun bindMainViewModel(viewModel: PostListViewModel): ViewModel
}