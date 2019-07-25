package com.carllewis14.repos.di.module


import com.carllewis14.repos.ui.ListFragment
import com.carllewis14.repos.ui.RepoDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector



/**
 *
 */
@Module
abstract class MainFragmentBindingModule {


    @ContributesAndroidInjector
    abstract fun provideListFragment(): ListFragment

    @ContributesAndroidInjector
    abstract fun provideDetailsFragment(): RepoDetailsFragment
}