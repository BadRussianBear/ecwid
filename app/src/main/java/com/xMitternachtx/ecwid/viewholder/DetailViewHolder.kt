package com.xMitternachtx.ecwid.viewholder

import android.view.View
import androidx.databinding.DataBindingUtil
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import com.xMitternachtx.ecwid.databinding.DetailBodyBinding
import com.xMitternachtx.ecwid.model.Product

class DetailViewHolder(view: View) :
        BaseViewHolder(view) {

    private lateinit var product: Product
    private val binding by lazy { DataBindingUtil.bind<DetailBodyBinding>(view) }

    @Throws(Exception::class)
    override fun bindData(data: Any) {
        if (data is Product) {
            product = data
            drawUI()
        }
    }

    private fun drawUI() {
        binding?.singleProduct = product
        binding?.executePendingBindings()
    }

    override fun onClick(view: View) = Unit

    override fun onLongClick(view: View) = false
}