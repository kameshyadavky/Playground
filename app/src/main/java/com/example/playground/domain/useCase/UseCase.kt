package com.example.playground.domain.useCase


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.playground.util.DefaultScheduler
import com.example.playground.domain.result.Result
/**
 * Executes business logic synchronously or asynchronously using a [Scheduler].
 *
 * @use
 *      UseCaseobject(pass LiveData)
 *
 *      in subclass:
 *      execute method will be implemented
 *
 *      override fun execute(parameters: Unit) = repository.getAgenda()//returns List
 *      this list will be handled asynchronously
 */
abstract class UseCase<in P, R> {

    private val taskScheduler = DefaultScheduler

    /** Executes the use case asynchronously and places the [Result] in a MutableLiveData
     *
     * @param parameters the input parameters to run the use case with
     * @param result the MutableLiveData where the result is posted to
     *
     */
    operator fun invoke(parameters: P, result: MutableLiveData<R>) {
        // result.value = Result.Loading TODO: add data to Loading to avoid glitches
        try {
            taskScheduler.execute {
                try {
                    execute(parameters).let { useCaseResult ->
                        result.postValue(useCaseResult)
                    }
                } catch (e: Exception) {
                }
            }
        } catch (e: Exception) {
        }
    }

    /** Executes the use case asynchronously and returns a [Result] in a new LiveData object.
     *
     * @return an observable [LiveData] with a [Result].
     *
     * @param parameters the input parameters to run the use case with
     */
    operator fun invoke(parameters: P): LiveData<R> {
        val liveCallback: MutableLiveData<R> = MutableLiveData()
        this(parameters, liveCallback)
        return liveCallback
    }
/*
    *//** Executes the use case synchronously  *//*
    fun executeNow(parameters: P): R {
        return try {
            Result.Success(execute(parameters))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }*/

    /**
     * Override this to set the code to be executed.
     */
    @Throws(RuntimeException::class)
    protected abstract fun execute(parameters: P): R
}

operator fun <R> UseCase<Unit, R>.invoke(): LiveData<R> = this(Unit)
operator fun <R> UseCase<Unit, R>.invoke(result: MutableLiveData<R>) = this(Unit, result)