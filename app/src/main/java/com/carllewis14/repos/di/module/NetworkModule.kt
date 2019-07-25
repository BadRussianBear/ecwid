package com.carllewis14.repos.di.module

import com.carllewis14.repos.network.GitHubRepo
import com.carllewis14.repos.util.BASEURL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * Network Module for api
 */

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASEURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }


    @Singleton
    @Provides
    fun provideRetrofitService(retrofit: Retrofit): GitHubRepo {
        return retrofit.create<GitHubRepo>(GitHubRepo::class.java)
    }
}
