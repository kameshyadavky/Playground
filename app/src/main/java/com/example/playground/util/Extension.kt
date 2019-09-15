package com.example.playground.util


import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.playground.ui.posts.PostListFragment
import java.util.concurrent.Executor


/**
 * For Activities
 * declaration is like
 *
 * var viewModel = viewModelProvider(viewModelFactory)
 */
inline fun <reified VM: ViewModel> FragmentActivity.viewModelProvider(
    provider: ViewModelProvider.Factory
) = ViewModelProvider(this,provider).get(VM::class.java)

/**
 * For Fragments
 * declaration is like
 *
 * var viewModel = viewModelProvider(viewModelFactory)
 */
inline fun < reified VM : ViewModel> Fragment.viewModelProvider(
    provider : ViewModelProvider.Factory
) = ViewModelProvider(this, provider).get(VM::class.java)


/**
 * Created extension function for LiveData
 */
fun <T: Any> LiveData<T>.observe( owner: LifecycleOwner, observer: (t: T) -> Unit) {

    observe(owner, Observer { observer(it) })

}
/**
 * Create extension function for LifecycleOwner
 * Can be used only in classes that are implementing LifecycleOwner
 * @param T defined for type of data LiveData holding
 * @doc body is the function to apply after LiveData is changed
 *
 * observe(owner: this){ it : T... function to apply...}
 * [observe] used in @see [PostListFragment.onViewCreated]
 *
 * explain:
 *      <> : Type a function require
 *      <T, L: LiveData<T>> : Type of data to perform action on
 *                        L : Type of data to observe
 *      Unit : function to apply or any value to change on type T
 */

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T) -> Unit) {
    liveData.observe(this, Observer{body(it)})
}

/**
 * Extension function map for LiveData
 * Explain:
 *      <X,Y> X: type of LiveData to perform map function on
 *            Y: type of LiveData to return
 *      body: (X) -> Y : apply function to value of LiveData of type X
 *                       return Y type of LiveData
 */
fun <X, Y> LiveData<X>.map(body: (X) -> Y): LiveData<Y> {
    return Transformations.map(this, body)
}

fun ImageView.load(uri: Uri?){
    Glide.with(context).load(uri)
        .apply(
            RequestOptions()
                .circleCrop()
        )
        .listener(object : RequestListener<Drawable>{
            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                return false
                }

            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

        })
        .into(this)
}


inline fun <reified T : ViewModel> Fragment.viewModelWithLiveData(factory: ViewModelProvider.Factory, body: T.() -> Unit): T {
    val vm = ViewModelProvider(this, factory)[T::class.java]
    vm.body()
    return vm
}

