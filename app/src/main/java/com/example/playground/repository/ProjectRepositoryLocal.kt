package com.example.playground.repository


import android.content.Context
import androidx.lifecycle.MutableLiveData
import android.os.Handler
import android.widget.Toast
import com.example.playground.model.Post
import com.example.playground.network.PostApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Named

class ProjectRepositoryLocal @Inject constructor(private var retrofitApiSerivice : PostApi,
                                                 @Named("AppContext") var context : Context
) {


    var data =  MutableLiveData<List<Post>>()
    private lateinit var subscription : Disposable

    // Function to return data to viewmodel
    fun refreshData(onDataReadyCallback: OnDataReadyCallback) {
        Handler().postDelayed({ onDataReadyCallback.onDataReady("new data") },2000)
    }

    fun getRepositories(onRepositoryReadyCallback: OnLocalRepositoryReadyCallback) {
        var arrayList = ArrayList<Post>()
        arrayList.add(Post(1478, 1, "First" , "false"))
        arrayList.add(Post(1478, 1, "First" , "false"))
        arrayList.add(Post(1478, 1, "First" , "false"))

        Handler().postDelayed({ onRepositoryReadyCallback.onDataReady(arrayList) },2000)
    }

    fun getLiveRepositories(): Observable<ArrayList<Post>> {
        var arrayList = ArrayList<Post>()
        arrayList.add(Post(1478, 11, "First" , "false"))
        arrayList.add(Post(1478, 12, "First" , "false"))
        arrayList.add(Post(1478, 13, "First" , "false"))

        return Observable.just(arrayList).delay ( 2, TimeUnit.SECONDS)

    }

    fun getLiveDataRepository(){

        var arrayList = ArrayList<Post>()
        arrayList.add(Post(1478, 1, "First" , "false"))
        arrayList.add(Post(1478, 2, "First" , "false"))
        arrayList.add(Post(1478, 3, "First" , "false"))
        data.value = arrayList
    }

    fun getRetrofitData(){
        subscription = retrofitApiSerivice.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> data.value = result },
                { error -> Toast.makeText(context, error.toString(),Toast.LENGTH_SHORT).show()}
            )

    }
    fun saveRepositories(arrayList: ArrayList<Post>){
        //todo save repositories in DB
    }

}

interface OnDataReadyCallback {
    fun onDataReady(data : String)
}

interface OnLocalRepositoryReadyCallback {
    fun onDataReady(data : ArrayList<Post>)
}