package com.example.playground.repository


import android.os.Handler
import com.example.playground.model.Post
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ProjectRepositoryLocal @Inject constructor() {

    // Function to return data to viewmodel
    fun refreshData(onDataReadyCallback: OnDataReadyCallback) {
        Handler().postDelayed({ onDataReadyCallback.onDataReady("new data") },2000)
    }

    fun getRepositories(onRepositoryReadyCallback: OnLocalRepositoryReadyCallback) {
        var arrayList = ArrayList<Post>()
        arrayList.add(Post(1478, 1, "First" , false))
        arrayList.add(Post(1478, 1, "First" , false))
        arrayList.add(Post(1478, 1, "First" , false))

        Handler().postDelayed({ onRepositoryReadyCallback.onDataReady(arrayList) },2000)
    }

    fun getLiveRepositories(): Observable<ArrayList<Post>> {
        var arrayList = ArrayList<Post>()
        arrayList.add(Post(1478, 1, "First" , false))
        arrayList.add(Post(1478, 2, "First" , false))
        arrayList.add(Post(1478, 3, "First" , false))

        return Observable.just(arrayList).delay ( 2, TimeUnit.SECONDS)

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