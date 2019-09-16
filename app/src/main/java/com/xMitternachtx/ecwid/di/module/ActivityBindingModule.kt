package com.xMitternachtx.ecwid.di.module

import com.xMitternachtx.ecwid.ui.AddProdActivity
import com.xMitternachtx.ecwid.ui.DetailActivity
import com.xMitternachtx.ecwid.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun contributeDetailActivity(): DetailActivity

    @ContributesAndroidInjector
    internal abstract fun contributeAddProdActivity(): AddProdActivity
}