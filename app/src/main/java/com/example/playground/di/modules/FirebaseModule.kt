package com.example.playground.di.modules

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FirebaseModule {

    @Provides
    fun provideFirebaseAuthClient(): FirebaseAuth{
        return FirebaseAuth.getInstance()
    }
}