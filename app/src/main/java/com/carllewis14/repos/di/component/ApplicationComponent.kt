package com.carllewis14.repos.di.component

import android.app.Application
import com.carllewis14.repos.base.BaseApplication
import com.carllewis14.repos.di.module.ActivityBindingModule
import com.carllewis14.repos.di.module.ContextModule
import com.carllewis14.repos.di.module.NetworkModule
import com.carllewis14.repos.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton





/**
 *
 */
@Singleton
@Component(modules = [(ContextModule::class), (NetworkModule::class), (ViewModelModule::class), (AndroidSupportInjectionModule::class), (ActivityBindingModule::class)])
interface ApplicationComponent : AndroidInjector<DaggerApplication> {

    /**
     *
     * Inject dependencies into Base Application
     */
    fun inject(application: BaseApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }
}