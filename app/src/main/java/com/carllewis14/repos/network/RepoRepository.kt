package com.carllewis14.repos.network

import com.carllewis14.repos.model.Repo
import io.reactivex.Single
import javax.inject.Inject



/**
 *
 */
class RepoRepository @Inject constructor(var repoService: GitHubRepo) {





    fun getRepositories(): Single<List<Repo>> {
        return repoService.getRepos()
    }

    fun getSingleRepo(owner: String, name: String): Single<Repo> {
        return repoService.getSingleRepo(owner, name)
    }
}