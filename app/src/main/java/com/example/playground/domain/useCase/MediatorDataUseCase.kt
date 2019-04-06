package com.example.playground.domain.useCase

import androidx.lifecycle.MediatorLiveData

abstract class MediatorDataUseCase<M>{

    protected val result = MediatorLiveData<M>()

    open fun observe(): MediatorLiveData<M> {
        return result
    }

    abstract fun execute()

}