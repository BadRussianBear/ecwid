package com.xMitternachtx.ecwid.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class AddProdActivityViewModel @Inject
constructor() : ViewModel() {
    private val name: MutableLiveData<String> = MutableLiveData()

    init {
    }
}