package com.example.playground.ui.login

import androidx.lifecycle.ViewModel
import com.example.playground.util.diUtil.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class LoginActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginActivityViewModel::class)
    abstract fun bindMainViewModel(viewModel: LoginActivityViewModel): ViewModel
}