package com.example.shared.data.work

import androidx.work.DelegatingWorkerFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlaygroundWorkerFactory @Inject constructor()
    : DelegatingWorkerFactory() {
    init {

    }
}