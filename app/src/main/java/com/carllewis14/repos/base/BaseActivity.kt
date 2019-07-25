package com.carllewis14.repos.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.Nullable
import butterknife.ButterKnife
import dagger.android.support.DaggerAppCompatActivity


/**
 *
 */
abstract class BaseActivity: DaggerAppCompatActivity() {

    /**
     * gets resource layout by id
     */
    @LayoutRes
    abstract fun layoutRes(): Int

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes())
        ButterKnife.bind(this)
    }
}