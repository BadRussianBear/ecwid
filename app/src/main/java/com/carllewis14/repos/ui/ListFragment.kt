package com.carllewis14.repos.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.NonNull
import android.support.annotation.Nullable
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import com.carllewis14.repos.R
import com.carllewis14.repos.adapter.RepoListAdapter
import com.carllewis14.repos.adapter.RepoSelectedListener
import com.carllewis14.repos.base.BaseFragment
import com.carllewis14.repos.model.Repo
import com.carllewis14.repos.viewmodels.DetailsViewModel
import com.carllewis14.repos.viewmodels.ListViewModel
import com.carllewis14.repos.viewmodels.ViewModelFactory
import javax.inject.Inject


/**
 * List Fragment
 */
class ListFragment : BaseFragment(), RepoSelectedListener {

    @BindView(R.id.repoRecyclerView)
    lateinit var listView: RecyclerView
    @BindView(R.id.tv_error)
    lateinit var errorTextView: TextView
    @BindView(R.id.loading_view)
    lateinit var loadingView: View


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var viewModel: ListViewModel? = null

    override fun layoutRes(): Int {
        return R.layout.screen_list
    }

    override fun onViewCreated(@NonNull view: View, @Nullable savedInstanceState: Bundle?) {

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListViewModel::class.java)

        listView.addItemDecoration(DividerItemDecoration(getBaseActivity()!!, DividerItemDecoration.VERTICAL))
        listView.adapter = RepoListAdapter(viewModel!!, this, this)
        listView.layoutManager = LinearLayoutManager(context)

        observableViewModel()
    }

    override fun onRepoSelected(repo: Repo) {
        val detailsViewModel = ViewModelProviders.of(getBaseActivity()!!, viewModelFactory).get(DetailsViewModel::class.java)
        detailsViewModel.setSelectedRepo(repo)
       getBaseActivity()?.supportFragmentManager?.beginTransaction()?.replace(R.id.screenContainer, RepoDetailsFragment())
                ?.addToBackStack(null)?.commit()
    }

    private fun observableViewModel() {
        viewModel?.getRepos()?.observe(this, Observer<List<Repo>>{ repos -> if (repos != null) listView.visibility = View.VISIBLE })

        viewModel?.getError()?.observe(this, Observer<Boolean>{ isError ->
            if (isError != null)
                if (isError) {
                    errorTextView.visibility = View.VISIBLE
                    listView.visibility = View.GONE
                    errorTextView.text = getString(R.string.error_load_data)
                } else {
                    errorTextView.visibility = View.GONE
                    errorTextView.text = null
                }
        })

        viewModel?.getLoading()?.observe(this, Observer<Boolean>{ isLoading ->
            if (isLoading != null) {
                loadingView.visibility = if (isLoading) View.VISIBLE else View.GONE
                if (isLoading) {
                    errorTextView.visibility = View.GONE
                    listView.visibility = View.GONE
                }
            }
        })
    }




}


