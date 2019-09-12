package com.xMitternachtx.ecwid.adapter

import android.view.View
import com.xMitternachtx.ecwid.R
import com.xMitternachtx.ecwid.model.Product
import com.xMitternachtx.ecwid.viewholder.DetailViewHolder
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.SectionRow

@Suppress("PrivatePropertyName")
class DetailAdapter : BaseAdapter() {

    private val section_itemDetail = 2

    fun addItemDetailList(product: Product) {
        addItemOnSection(section_itemDetail, product)
        notifyDataSetChanged()
    }


    override fun layout(sectionRow: SectionRow) = R.layout.detail_body

    override fun viewHolder(layout: Int, view: View) = DetailViewHolder(view)
}