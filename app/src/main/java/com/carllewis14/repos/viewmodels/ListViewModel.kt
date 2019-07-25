package com.carllewis14.repos.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.carllewis14.repos.model.Repo
import com.carllewis14.repos.network.RepoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject



/**
 *
 *  List view model for fragment that display list of retrieved repos
 *
 */
class ListViewModel @Inject constructor(val repoRepository: RepoRepository): ViewModel() {


    private var disposable: CompositeDisposable? = null

    val repos = MutableLiveData<List<Repo>>()
    val repoLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

   init {

        disposable = CompositeDisposable()
        fetchRepos()
    }

    fun getRepos(): LiveData<List<Repo>> {
        return repos
    }

    fun getError(): LiveData<Boolean> {
        return repoLoadError
    }

    fun getLoading(): LiveData<Boolean> {
        return loading
    }

    //Fetch repos function
    private fun fetchRepos() {
        loading.value = true
        disposable?.add(repoRepository.getRepositories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Repo>>() {
                    override fun onSuccess(value: List<Repo>) {
                        repoLoadError.value = false
                        repos.value = value
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        repoLoadError.value = true
                        loading.value = false
                    }
                }))
    }


    //clear disposables to prevent memory leaks
    override fun onCleared() {
        super.onCleared()
        if (disposable != null) {
            disposable!!.clear()
            disposable = null
        }
    }
}