package com.xMitternachtx.ecwid.adapter

import android.view.View
import com.xMitternachtx.ecwid.R
import com.xMitternachtx.ecwid.model.Product
import com.xMitternachtx.ecwid.viewholder.ProductViewHolder
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import com.skydoves.baserecyclerviewadapter.SectionRow

@Suppress("PrivatePropertyName", "MemberVisibilityCanBePrivate")
class ProductAdapter(
        private val delegate: ProductViewHolder.Delegate
) : BaseAdapter() {

    init {
        addSection(ArrayList<Product>())
    }

    fun addFollowList(followers: List<Product>) {
        sections()[0].addAll(followers)
        notifyDataSetChanged()
    }


    fun delItemFromList(product: Product, position: Int){
        //removeItemOnSection(0, product)
        //notifyItemRemoved(position)
        sections()[0].clear()
    }

    fun clearAll() {
        sections()[0].clear()
        notifyDataSetChanged()
    }

    override fun layout(sectionRow: SectionRow): Int {
        return R.layout.view_repo_list_item
    }

    override fun viewHolder(layout: Int, view: View): BaseViewHolder {
        return ProductViewHolder(view, delegate)

    }
}