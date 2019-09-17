package com.xMitternachtx.ecwid.viewholder

import android.view.View
import androidx.databinding.DataBindingUtil
import com.xMitternachtx.ecwid.databinding.ViewRepoListItemBinding
import com.xMitternachtx.ecwid.model.Product
import com.skydoves.baserecyclerviewadapter.BaseViewHolder


class ProductViewHolder(
        private val view: View,
        private val delegate: Delegate
) : BaseViewHolder(view) {

    interface Delegate {
        fun onItemClick(product: Product, view: View, adapterPosition: Int)
        fun onMoreClick(product: Product, view: View, adapterPosition: Int)
    }

    private lateinit var product: Product
    private val binding by lazy { DataBindingUtil.bind<ViewRepoListItemBinding>(view) }

    @Throws(Exception::class)
    override fun bindData(data: Any) {
        if (data is Product) {
            product = data
            drawUI()
        }
    }

    private fun drawUI() {
        binding?.product = product
        binding?.moreBtn?.setOnClickListener{
            delegate.onMoreClick(product, view, this.adapterPosition)
        }
        binding?.executePendingBindings()

    }

    override fun onClick(view: View) {
        delegate.onItemClick(product, view, this.adapterPosition)
    }

    override fun onLongClick(view: View) = false
}
