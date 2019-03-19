package com.example.playground.ui


import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import com.example.playground.base.BaseViewModel
import com.example.playground.model.Post
import com.example.playground.repository.OnDataReadyCallback
import com.example.playground.repository.ProjectRepositoryLocal
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class PostListViewModel @Inject constructor(private var projectRepositoryLocal: ProjectRepositoryLocal): BaseViewModel(){

    var text = ObservableField<String>("Old data")
    var isLoading = ObservableField<Boolean>(false)

    private lateinit var subscription: Disposable

    var repositories = MutableLiveData<ArrayList<Post>>()


    fun refresh(){
        isLoading.set(true)
        projectRepositoryLocal.refreshData(object : OnDataReadyCallback {
            override fun onDataReady(data: String) {
                isLoading.set(false)
                text.set(data)
            }
        })
    }

    fun loadRepositories() {



        subscription = projectRepositoryLocal.getLiveRepositories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { result -> onRetrievePostListSuccess(result) },
                { onRetrievePostListError() }
            )

    }

    private fun onRetrievePostListStart(){

        isLoading.set(true)
    }

    private fun onRetrievePostListFinish(){

        isLoading.set(false)
    }

    private fun onRetrievePostListSuccess(result : ArrayList<Post>){

        repositories.value = result

    }

    private fun onRetrievePostListError(){

    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}

