package com.carllewis14.repos.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

/**
 * Provide Context module
 */
@Module
abstract class ContextModule {

    @Binds
    abstract fun provideContext(application: Application): Context
}
