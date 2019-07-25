package com.carllewis14.repos.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.NonNull
import android.support.annotation.Nullable
import android.view.View
import com.carllewis14.repos.R
import com.carllewis14.repos.base.BaseFragment
import com.carllewis14.repos.model.Repo
import com.carllewis14.repos.viewmodels.DetailsViewModel
import com.carllewis14.repos.viewmodels.ViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.screen_details.*
import javax.inject.Inject


/**
 * Repo Details class
 */
class RepoDetailsFragment : BaseFragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var detailsViewModel: DetailsViewModel? = null

    override fun layoutRes(): Int {
        return R.layout.screen_details
    }

    override fun onViewCreated(@NonNull view: View, @Nullable savedInstanceState: Bundle?) {
        detailsViewModel = ViewModelProviders.of(getBaseActivity()!!, viewModelFactory).get(DetailsViewModel::class.java)
        detailsViewModel!!.restoreFromBundle(savedInstanceState)
        displayRepo()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        detailsViewModel?.saveToBundle(outState)
    }

    private fun displayRepo() {
        detailsViewModel?.getSelectedRepo()?.observe(this, Observer<Repo>{ repo ->
            if (repo != null) {

                Picasso.with(context).load(repo.owner?.avatar_url).into(iv_avatar_pic)
                tv_repo_name.text = repo.name
                tv_repo_description.text = repo.description
                tv_forks.text = repo.forks.toString()
                tv_stars.text = repo.stargazers_count.toString()
                tv_repos_url.text = repo.owner?.repos_url
            }
        })
    }
}