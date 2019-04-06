package com.example.playground.util.diUtil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider


/**
 * @use
 *      @Inject provider: ViewModelProvider.Factory
 *      ViewModelProviders.of(this,provider).get(VM::class.java)
 *
 * Provides ViewModel of any type whenever it is present in Dagger Map.
 * @param creators to be provided by Dagger. Holds all the (key, value) pair for ViewModel injected in Dagger
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(
    private val creators: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var creator: Provider<out ViewModel>? = creators[modelClass]
        if (creator == null) {
            for ((key, value) in creators) {
                // checks if there is a key associated with with modelClass(VM::class.java)
                //if present provide with its value
                if (modelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }
        // if there is no key present i.e. value is null throw error
        if (creator == null) {
            throw IllegalArgumentException("unknown model class " + modelClass)
        }
        try {
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}