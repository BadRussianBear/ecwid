package com.xMitternachtx.ecwid.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.xMitternachtx.ecwid.R
import com.xMitternachtx.ecwid.adapter.ProductAdapter
import com.xMitternachtx.ecwid.databinding.ActivityMainBinding
import com.xMitternachtx.ecwid.model.Product
import com.xMitternachtx.ecwid.util.vm
import com.xMitternachtx.ecwid.viewholder.ProductViewHolder
import com.xMitternachtx.ecwid.viewmodels.MainActivityViewModel
import com.xMitternachtx.ecwid.viewmodels.ViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_repo_list_item.*
import javax.inject.Inject


@Suppress("SpellCheckingInspection")
class MainActivity : AppCompatActivity(),
        ProductViewHolder.Delegate,
        CustomDialog.NoticeDialogListener,
        AddCustomDialog.NoticeDialogListener {


    override fun okClick() {

    }

    override fun cancelClick() {

    }

    override fun onRemoveClick(dialog: DialogFragment) {
        adapter.delItemFromList(viewModel.getProduct(), viewModel.getPosition())
        viewModel.delProd(viewModel.getProduct())
    }

    override fun onEditClick(dialog: DialogFragment) {
        showAddDialog(viewModel.getView() ,viewModel.getProduct())
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy { vm(viewModelFactory, MainActivityViewModel::class) }
    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { ProductAdapter(this) }


    var prefs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        checkFirstRun()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        main_fab.setOnClickListener{
            var product = Product(0, "", "", 0, "", "")
            showAddDialog(it, product)
        }
        main_recyclerView.adapter = adapter
        main_recyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun checkFirstRun() {
        prefs = getSharedPreferences("com.xMitternachtx", MODE_PRIVATE)
    }

    //dont ask, just inject to db
    //And SPECIAL THX to https://www.json-generator.com/
    override fun onResume() {
        super.onResume()
        if (prefs!!.getBoolean("firstrun", true)) {
            val file_name = "forDb.json"
            val json_string = application.assets.open(file_name).bufferedReader().use {
                it.readText()
            }
            val gson = Gson()
            val topic: List<Product> = gson.fromJson(json_string, Array<Product>::class.java).toList()
            viewModel.addProducts(topic)
            prefs!!.edit().putBoolean("firstrun", false).apply()
        }
    }

    override fun onItemClick(product: Product, view: View, adapterPosition: Int) {
        DetailActivity.startActivity(this, product)
    }

    override fun onMoreClick(product: Product, view: View, adapterPosition: Int) {
        viewModel.setProduct(product, adapterPosition)
        viewModel.setView(view)
        showDialog()
    }

    fun showDialog() {
        val fragmentManager = supportFragmentManager
        val newFragment = CustomDialog()
        newFragment.show(fragmentManager, "dialog")
    }

    fun showAddDialog(it: View, product: Product) {
        AddProdActivity.startActivity(this, it, product)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            AddProdActivity.intent_requestCode -> data?.let {
                viewModel.addProduct(it.getParcelableExtra("prod"))
                adapter.clearAll()
            }
        }
    }
}
