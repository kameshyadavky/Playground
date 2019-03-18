package com.example.playground.ui

import android.databinding.ObservableField
import com.example.playground.base.BaseViewModel
import com.example.playground.repository.OnDataReadyCallback
import com.example.playground.repository.ProjectRepository

class PostListViewModel(): BaseViewModel(){

    var projectModel: ProjectRepository = ProjectRepository()
    var text = ObservableField<String>("Old data")
    var isLoading = ObservableField<Boolean>(false)

    fun refresh(){
        isLoading.set(true)
        projectModel.refreshData(object : OnDataReadyCallback {
            override fun onDataReady(data: String) {
                isLoading.set(false)
                text.set(data)
            }
        })
    }
}

