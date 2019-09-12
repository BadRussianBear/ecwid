package com.xMitternachtx.ecwid.base

import com.xMitternachtx.ecwid.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


/**
 * Base Application Class
 */

class BaseApplication : DaggerApplication() {


    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? {
        val component = DaggerApplicationComponent.builder().application(this).build()
        component.inject(this)
        return component
    }


}
