package com.xMitternachtx.ecwid.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.xMitternachtx.ecwid.R
import com.xMitternachtx.ecwid.adapter.DetailAdapter
import com.xMitternachtx.ecwid.databinding.ScreenDetailsBinding
import com.xMitternachtx.ecwid.model.Product
import com.xMitternachtx.ecwid.util.observeLiveData
import com.xMitternachtx.ecwid.viewmodels.DetailActivityViewModel
import com.xMitternachtx.ecwid.viewmodels.ViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.toolbar.view.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(DetailActivityViewModel::class.java) }
    private val binding by lazy { DataBindingUtil.setContentView<ScreenDetailsBinding>(this, R.layout.screen_details) }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        supportPostponeEnterTransition()

        initializeListeners()
        initializeUI()
    }

    private fun initializeListeners() {
        binding.detailToolbar.toolbar_home?.setOnClickListener { onBackPressed() }
    }

    private fun initializeUI() {
        binding.detailToolbar.toolbar_title?.text = getProductNameFromIntent()
        viewModel.setProd(getProductNameFromIntent())
        observeLiveData(viewModel.info) { updateUI(it) }
    }

    private fun updateUI(product: Product) {
        binding.detailBody.singleProduct = product
    }

    private fun getProductNameFromIntent(): String {
        return intent.getStringExtra(product_name)
    }
    companion object {
        const val product_name = "name"

        fun startActivity(activity: Activity, product: Product, view: View) {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(product_name, product.name)
            //val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, view, ViewCompat.getTransitionName(view)!!)
            activity.startActivity(intent)
        }
    }
}
