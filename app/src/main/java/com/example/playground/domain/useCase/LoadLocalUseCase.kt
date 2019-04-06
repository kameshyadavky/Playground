package com.example.playground.domain.useCase

import com.example.playground.data.model.Post
import com.example.playground.data.repository.ProjectRepositoryLocal
import javax.inject.Inject

open class LoadLocalUseCase @Inject constructor(var projectRepositoryLocal : ProjectRepositoryLocal)
    :UseCase<Unit, List<Post>>(){
    override fun execute(parameters: Unit): List<Post> = projectRepositoryLocal.getRepositories()
}