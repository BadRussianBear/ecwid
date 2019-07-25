package com.carllewis14.repos.adapter

import com.carllewis14.repos.model.Repo



/**
 * Interface for repo selected
 */
interface RepoSelectedListener {

    fun onRepoSelected(repo: Repo)
}