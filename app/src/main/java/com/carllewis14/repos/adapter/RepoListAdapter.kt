package com.carllewis14.repos.adapter

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.carllewis14.repos.R
import com.carllewis14.repos.model.Repo
import com.carllewis14.repos.viewmodels.ListViewModel
import java.util.*


/**
 *
 */

class RepoListAdapter internal constructor(viewModel: ListViewModel, lifecycleOwner: LifecycleOwner, private val repoSelectedListener: RepoSelectedListener) : RecyclerView.Adapter<RepoListAdapter.RepoViewHolder>() {
    private val data = ArrayList<Repo>()

    init {
        viewModel.getRepos().observe(lifecycleOwner, Observer<List<Repo>>{ repos ->
            data.clear()
            if (repos != null) {
                data.addAll(repos
                )
                notifyDataSetChanged()
            }
        })
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_repo_list_item, parent, false)
        return RepoViewHolder(view, repoSelectedListener)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemId(position: Int): Long {
        return data[position].id
    }

    inner class RepoViewHolder(itemView: View, repoSelectedListener: RepoSelectedListener) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.tv_repo_name)
        lateinit var repoNameTextView: TextView
        @BindView(R.id.tv_repo_full_name)
        lateinit var repoDescriptionTextView: TextView
        @BindView(R.id.tv_forks)
        lateinit var forksTextView: TextView
        @BindView(R.id.tv_stars)
        lateinit var starsTextView: TextView

        private var repo: Repo? = null

        init {
            ButterKnife.bind(this, itemView)
            itemView.setOnClickListener { v ->
                if (repo != null) {
                    repoSelectedListener.onRepoSelected(repo!!)
                }
            }
        }

        fun bind(repo: Repo) {
            this.repo = repo

            repoNameTextView.text = repo.name
            repoDescriptionTextView.text = repo.full_name
            forksTextView.text = repo.forks.toString()
            starsTextView.text = repo.stargazers_count.toString()
        }
    }
}
