package com.xMitternachtx.ecwid.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.xMitternachtx.ecwid.model.Product
import com.xMitternachtx.ecwid.room.ProductRepository
import javax.inject.Inject

class DetailActivityViewModel @Inject
constructor(repository: ProductRepository) : ViewModel() {
    private val name: MutableLiveData<String> = MutableLiveData()
    val info: LiveData<Product>

    init {
        info = name.switchMap {
            name.value?.let { user -> repository.getProduct(user) }
                    ?: AbsentLiveData.create()
        }
    }
    fun setProd(prod: String) {
        name.value = prod
    }
}

class AbsentLiveData<T> : LiveData<T>() {
    init {
        postValue(null)
    }
    companion object {
        fun <T> create() = AbsentLiveData<T>()
    }
}
