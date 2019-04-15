package com.example.playground.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.example.playground.data.model.Post
import com.example.playground.data.network.PostApi
import com.example.playground.data.network.RoomApi
import com.google.firebase.firestore.FirebaseFirestore
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
) {
    private var subscription:Disposable? = null
    private var posts = MutableLiveData<List<Post>>()

    private val fireStore = FirebaseFirestore.getInstance()

    fun getFirestore(): LiveData<List<Post>>{

        fireStore.collection("users")
            .get()
            .addOnSuccessListener{ result->

                posts.value = result.toObjects(Post::class.java)
                for (document in result) {
                    Log.d("Hello", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener{
                Log.d("failed",it.toString())
            }

        return posts

    }
    fun getPosts() {
        subscription = retrofitApi.getPosts()
            .concatMap {
                roomApi.insertAll(*it.toTypedArray())
                Observable.just(it)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    //do something
                },
                { error -> Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show() }
            )
    }

    fun getDatabasePosts(): DataSource.Factory<Int, Post> = roomApi.getAllPosts()

    fun dispose() {
        if(subscription!= null){
            subscription?.dispose()
        }

    }

}
