package com.carllewis14.repos.network

import com.carllewis14.repos.model.Repo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Git hub repo interface network class
 */
interface GitHubRepo {

    @GET("orgs/Google/repos")
    fun getRepos(): Single<List<Repo>>

    @GET("repos/{owner}/{name}")
    fun getSingleRepo(@Path("owner") owner: String, @Path("name") name: String): Single<Repo>
}