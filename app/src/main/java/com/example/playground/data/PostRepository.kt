package com.example.playground.data

import android.content.Context
import androidx.lifecycle.MutableLiveData
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.example.playground.data.model.Post
import com.example.playground.data.network.PostApi
import com.example.playground.data.network.RoomApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executor
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
open class PostRepository @Inject constructor(
    private var retrofitApi: PostApi,
    @Named("AppContext") var context: Context,
    private var roomApi: RoomApi
) : PostRepo {

    private val ioExecutor: Executor? = null

    var data = MutableLiveData<List<Post>>()
    private lateinit var subscription: Disposable

    override fun getPosts(){
        subscription = retrofitApi.getPosts()
            .concatMap {
                roomApi.insertAll(*it.toTypedArray())
                Observable.just(it)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    data.value = result
                },
                { error -> Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show() }
            )
    }


    override fun getDatabasePosts(): DataSource.Factory<Int, Post> = roomApi.getAllPosts()


    private fun saveRepositories(arrayList: List<Post>) {
        ioExecutor?.execute {
            roomApi.insertAll(*arrayList.toTypedArray())
        }
    }

    override fun dispose() {
        subscription.dispose()
    }

}

interface PostRepo {
    fun getPosts()
    fun dispose()
    fun getDatabasePosts(): DataSource.Factory<Int, Post>
}
