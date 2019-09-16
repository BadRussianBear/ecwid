package com.xMitternachtx.ecwid.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.xMitternachtx.ecwid.R
import com.xMitternachtx.ecwid.databinding.ActivityMainBinding
import com.xMitternachtx.ecwid.databinding.AddProdBinding
import com.xMitternachtx.ecwid.model.Product
import com.xMitternachtx.ecwid.util.circularRevealed
import com.xMitternachtx.ecwid.util.circularUnRevealed
import com.xMitternachtx.ecwid.viewmodels.AddProdActivityViewModel
import com.xMitternachtx.ecwid.viewmodels.ViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.add_prod.*
import kotlinx.android.synthetic.main.toolbar.view.*
import javax.inject.Inject

class AddProdActivity: AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(AddProdActivityViewModel::class.java) }
    private lateinit var binding: AddProdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.add_prod)
        binding.lifecycleOwner = this
        initializeListeners()
        startCircularRevealed(savedInstanceState)
    }

    private fun initializeListeners() {
        binding.detailToolbar.toolbar_home?.toolbar_title?.text = "Add product"
        binding.detailToolbar.toolbar_home?.setOnClickListener { onBackPressed() }
        binding.btnSignup.setOnClickListener{

            var num = {
                if(input_number.text.toString().equals("")){
                    0
                }else{
                    Integer.parseInt(input_number.text.toString())
                }
            };
            var new_prod = Product(0, input_name.text.toString(), input_pay.text.toString(), num.invoke(), input_description.text.toString(), "")
            intent.putExtra("AN_OBJECT", new_prod)
            setResult(intent_requestCode, intent)
            onBackPressed()
        }
    }

    private fun startCircularRevealed(savedInstanceState: Bundle?) {
        if(savedInstanceState == null) {
            parent_layout.visibility = View.INVISIBLE

            val viewTreeObserver = parent_layout.viewTreeObserver
            if (viewTreeObserver.isAlive) {
                viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        circularRevealed(parent_layout, parent_layout.width, 0)
                        parent_layout.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    }
                })
            }
        }
    }

    override fun onBackPressed() {
        circularUnRevealed(parent_layout, parent_layout.width, 0)
        super.onBackPressed()
    }

    companion object {
        const val intent_requestCode = 1001

        fun startActivity(activity: Activity, view: View) {
            val intent = Intent(activity, AddProdActivity::class.java)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, view, ViewCompat.getTransitionName(view)!!)
            activity.startActivityForResult(intent, intent_requestCode, options.toBundle())
        }
    }

}