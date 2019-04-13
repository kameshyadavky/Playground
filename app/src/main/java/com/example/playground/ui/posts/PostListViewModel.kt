package com.example.playground.ui.posts


import androidx.lifecycle.*
import androidx.databinding.ObservableField
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.playground.domain.useCase.loadposts.LoadPostUseCase
import com.example.playground.data.model.Post
import com.example.playground.util.map
import javax.inject.Inject


class PostListViewModel @Inject constructor(var loadPostUseCase : LoadPostUseCase): ViewModel(){

    //var text = ObservableField<String>("Old data")
    var isLoading = ObservableField<Boolean>(false)

    var repositories = loadPostUseCase.pagedListLiveData().map {
        passLiveDataToUI(it)
    }

    fun loadRxRepositories() {
        loadPostUseCase.refreshData()
        //isLoading.set(false)
    }

    private fun passLiveDataToUI(arrayList: PagedList<Post>):PagedList<Post>{
        isLoading.set(false)
        return arrayList
    }

    override fun onCleared() {
        super.onCleared()
        loadPostUseCase.dispose()
    }
}

