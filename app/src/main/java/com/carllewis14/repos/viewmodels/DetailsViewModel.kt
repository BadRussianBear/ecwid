package com.carllewis14.repos.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.Bundle
import com.carllewis14.repos.model.Repo
import com.carllewis14.repos.network.RepoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject



/**
 *.
 */
class DetailsViewModel @Inject constructor(private val repoRepository: RepoRepository) : ViewModel() {
    private var disposable: CompositeDisposable? = null

    private val selectedRepo = MutableLiveData<Repo>()

    fun getSelectedRepo(): LiveData<Repo> {
        return selectedRepo
    }

    init {
        disposable = CompositeDisposable()
    }

    fun setSelectedRepo(repo: Repo) {
        selectedRepo.value = repo
    }

    fun saveToBundle(outState: Bundle) {
        if (selectedRepo.value != null) {
            outState.putStringArray("repo_details", arrayOf(selectedRepo.value?.owner?.login, selectedRepo.value?.name,
                    selectedRepo.value?.forks.toString(), selectedRepo.value?.stargazers_count.toString(), selectedRepo.value?.owner?.avatar_url, selectedRepo.value?.owner?.repos_url))
        }
    }

    fun restoreFromBundle(savedInstanceState: Bundle?) {
        if (selectedRepo.value == null) {
            if (savedInstanceState != null && savedInstanceState.containsKey("repo_details")) {
                loadRepo(savedInstanceState.getStringArray("repo_details"))
            }
        }
    }

    private fun loadRepo(repo_details: Array<String>?) {
        disposable!!.add(repoRepository.getSingleRepo(repo_details!![0], repo_details[1]).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(object : DisposableSingleObserver<Repo>() {
                    override fun onSuccess(value: Repo) {
                        selectedRepo.value = value
                    }

                    override fun onError(e: Throwable) {

                    }
                }))
    }

    override fun onCleared() {
        super.onCleared()
        if (disposable != null) {
            disposable!!.clear()
            disposable = null
        }
    }
}
