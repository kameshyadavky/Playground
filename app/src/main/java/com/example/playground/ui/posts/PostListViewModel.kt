package com.example.playground.ui.posts


import androidx.lifecycle.*
import androidx.databinding.ObservableField
import com.example.playground.model.Post
import com.example.playground.repository.OnDataReadyCallback
import com.example.playground.repository.ProjectRepositoryLocal
import javax.inject.Inject


class PostListViewModel @Inject constructor(private var projectRepositoryLocal: ProjectRepositoryLocal): ViewModel(){

    var text = ObservableField<String>("Old data")
    var isLoading = ObservableField<Boolean>(false)


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
        projectRepositoryLocal.getRetrofitData()

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

    fun passLivedatatoUI(arrayList: List<Post>):List<Post>{
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

 /*  Mediator Live data to listen from two data sources
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
*/
    override fun onCleared() {
        super.onCleared()
        //subscription.dispose()
    }
}

