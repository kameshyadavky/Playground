package com.example.playground.domain.result

import java.lang.Exception

/**
 * A sealed class allows you to represent constrained hierarchies
 * in which an object can only be of one of the given types.
 */
sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading: Result<Nothing>()

    override fun toString(): String {
        return when(this){
            is Success<*> -> "Success[data = $data]"
            is Error -> "Error[exception = $exception]"
            Loading -> "loading"
        }
    }
}

val Result<*>.succeded
    get() = this is Result.Success && data!= null