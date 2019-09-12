package com.xMitternachtx.ecwid.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xMitternachtx.ecwid.adapter.DetailAdapter
import com.xMitternachtx.ecwid.adapter.ProductAdapter
import com.xMitternachtx.ecwid.model.Product
import com.xMitternachtx.ecwid.viewmodels.MainActivityViewModel

@BindingAdapter("adapterFollowers", "viewModel")
fun bindAdapterFollowers(view: RecyclerView, resource: List<Product>?, viewModel: MainActivityViewModel) {
    if (resource != null) {
        val adapter = view.adapter as? ProductAdapter
        adapter?.addFollowList(resource)
    }
}

@BindingAdapter("adapterInfo", "viewModel")
fun bindAdapterInfo(view: RecyclerView, resource: List<Product>?, viewModel: MainActivityViewModel) {
    if (resource != null) {
        val adapter = view.adapter as? ProductAdapter
        adapter?.addFollowList(resource)
    }
}