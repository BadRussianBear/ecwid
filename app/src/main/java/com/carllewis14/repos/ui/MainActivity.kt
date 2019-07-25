package com.carllewis14.repos.ui

import android.os.Bundle
import com.carllewis14.repos.R
import com.carllewis14.repos.base.BaseActivity
import dagger.android.AndroidInjection


class MainActivity : BaseActivity() {


    override fun layoutRes(): Int {
        return R.layout.activity_main
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.screenContainer, ListFragment()).commit()

        }


    }

}
