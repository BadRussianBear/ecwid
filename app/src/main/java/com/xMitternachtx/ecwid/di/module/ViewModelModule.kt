package com.xMitternachtx.ecwid.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xMitternachtx.ecwid.util.ViewModelKey
import com.xMitternachtx.ecwid.viewmodels.DetailActivityViewModel
import com.xMitternachtx.ecwid.viewmodels.MainActivityViewModel
import com.xMitternachtx.ecwid.viewmodels.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    internal abstract fun bindMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailActivityViewModel::class)
    internal abstract fun bindDetailActivityViewModel(detailActivityViewModel: DetailActivityViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}