package com.example.playground.domain.useCase.loadposts


import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.playground.data.PostRepository
import com.example.playground.data.model.Post
import javax.inject.Inject

open class LoadPostUseCase @Inject constructor(
    private val repository: PostRepository
){


    /**
     * This function makes request call and store result in database
     */
    fun refreshData() {
        repository.getPosts()
    }

    fun pagedListLiveData(): LiveData<PagedList<Post>> {
        val roomPagedFactory = repository.getDatabasePosts()
        val pageListConfig = PagedList.Config.Builder()
            .setPageSize(20)
            .build()
        val data = LivePagedListBuilder(roomPagedFactory, pageListConfig)
        return data.build()
    }

    /**
     * dispose of subscriber in repository
     */
    fun dispose(){
        repository.dispose()
    }

}