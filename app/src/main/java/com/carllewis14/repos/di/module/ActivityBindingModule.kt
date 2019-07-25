package com.carllewis14.repos.di.module

import com.carllewis14.repos.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Main Activitiy binding module
 */

@Module
abstract class ActivityBindingModule {



    @ContributesAndroidInjector(modules = arrayOf(MainFragmentBindingModule::class ))
    abstract fun bindMainActivity(): MainActivity
}