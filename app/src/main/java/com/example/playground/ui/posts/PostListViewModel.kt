package com.example.playground.ui.posts


import androidx.lifecycle.*
import androidx.databinding.ObservableField
import com.example.playground.domain.useCase.LoadPostUseCase
import com.example.playground.data.model.Post
import com.example.playground.domain.useCase.LoadLocalUseCase
import com.example.playground.util.map
import javax.inject.Inject


class PostListViewModel @Inject constructor(var loadPostUseCase : LoadPostUseCase): ViewModel(){

    //var text = ObservableField<String>("Old data")
    var isLoading = ObservableField<Boolean>(false)


    var repositories = loadPostUseCase.observe().map {
        passLiveDataToUI(it)
    }

    //var redundant = MutableLiveData<List<Post>>()
/*
    val redundant = projectRepositoryLocal.data.map {
        data -> passLiveDataToUI(data)
    }*/

    /*val redundant = Transformations.map(projectRepositoryLocal.data){
        data->passLiveDataToUI(data)
    }*/


/*
    fun refresh(){
        isLoading.set(true)
        projectRepositoryLocal.refreshData(object : OnDataReadyCallback {
            override fun onDataReady(data: String) {
                isLoading.set(false)
                text.set(data)
            }
        })
    }*/

    fun loadRxRepositories() {


        //isLoading.set(false)
        loadPostUseCase.execute()

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

    private fun passLiveDataToUI(arrayList: List<Post>):List<Post>{
        isLoading.set(false)
        return arrayList
    }
    /*private fun onRetrievePostListStart(){

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

 *//*  Mediator Live data to listen from two data sources
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
*//*
    override fun onCleared() {
        super.onCleared()
        //subscription.dispose()
    }*/
}

