package com.xMitternachtx.ecwid.di.component

import android.app.Application
import com.xMitternachtx.ecwid.base.BaseApplication
import com.xMitternachtx.ecwid.di.module.ActivityBindingModule
import com.xMitternachtx.ecwid.di.module.ContextModule
import com.xMitternachtx.ecwid.di.module.DbModule
import com.xMitternachtx.ecwid.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


/**
 *
 */
@Singleton
@Component(modules = [(ContextModule::class), (DbModule::class), (ViewModelModule::class), (AndroidSupportInjectionModule::class), (ActivityBindingModule::class)])
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