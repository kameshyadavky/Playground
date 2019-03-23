package com.example.playground.ui


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
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
    //var redundant : LiveData<ArrayList<Post>> = MutableLiveData<ArrayList<Post>>()


    val redundant = Transformations.map(projectRepositoryLocal.data){
        data->passLivedatatoUI(data)
    }



    fun refresh(){
        isLoading.set(true)
        projectRepositoryLocal.refreshData(object : OnDataReadyCallback {
            override fun onDataReady(data: String) {
                isLoading.set(false)
                text.set(data)
            }
        })
    }

    fun loadRxRepositories() {


        isLoading.set(true)
        projectRepositoryLocal.getLiveDataRepository()
        /**
        subscription = projectRepositoryLocal.getLiveRepositories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { result -> onRetrievePostListSuccess(result) },
                { onRetrievePostListError() }
            )
        **/

    }

    fun passLivedatatoUI(arrayList: ArrayList<Post>):ArrayList<Post>{
        isLoading.set(false)
        return arrayList
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

    fun blogpostBoilerplateExample(): LiveData<ArrayList<Post>> {


        val result = MediatorLiveData<ArrayList<Post>>()

        result.addSource(repositories) { value ->
            result.value = value
        }
        result.addSource(redundant) { value ->
            result.value = value
        }
        return result
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}

