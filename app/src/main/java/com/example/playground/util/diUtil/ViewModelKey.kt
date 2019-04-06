package com.example.playground.util.diUtil

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass
import com.example.playground.ui.posts.PostListFragmentModule
/**
 * Creates a key associated with provided ViewModel with the use of @MapKey
 * @use
 *      @ViewModelKey(ViewModelName::class)
 * @see [PostListFragmentModule]
 *
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)
