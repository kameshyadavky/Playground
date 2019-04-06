package com.example.playground.domain.useCase



import com.example.playground.data.model.Post
import com.example.playground.data.repository.ProjectRepositoryLocal
import javax.inject.Inject

open class LoadPostUseCase @Inject constructor(
    private val repositoryLocal: ProjectRepositoryLocal
): MediatorDataUseCase<List<Post>>(){

    override fun execute() {
        val updateResult =  repositoryLocal.getRetrofitData()

        result.removeSource(updateResult)
        result.addSource(updateResult){
            result.postValue(it)
        }
    }

}