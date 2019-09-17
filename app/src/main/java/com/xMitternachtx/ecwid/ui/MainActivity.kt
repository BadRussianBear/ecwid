package com.xMitternachtx.ecwid.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
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
import javax.inject.Inject


@Suppress("SpellCheckingInspection")
class MainActivity : AppCompatActivity(),
        ProductViewHolder.Delegate,
        CustomDialog.NoticeDialogListener{

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy { vm(viewModelFactory, MainActivityViewModel::class) }
    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { ProductAdapter(this) }


    private var prefs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        checkFirstRun()
        setBindings()
        setUI()
    }

    private fun setBindings(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun setUI(){
        main_fab.setOnClickListener{
            val product = Product(0, "", "", 0, "", "")
            showAddDialog(it, product)
        }
        main_recyclerView.adapter = adapter
        main_recyclerView.layoutManager = LinearLayoutManager(this)
        main_recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(main_recyclerView, dx, dy)
                if (dy > 0 && main_fab.visibility == View.VISIBLE) {
                    main_fab.hide()
                } else if (dy < 0 && main_fab.visibility != View.VISIBLE) {
                    main_fab.show()
                }
            }
        })
    }

    private fun checkFirstRun() {
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

    private fun showDialog() {
        val fragmentManager = supportFragmentManager
        val newFragment = CustomDialog()
        newFragment.show(fragmentManager, "dialog")
    }

    private fun showAddDialog(it: View, product: Product) {
        AddProdActivity.startActivity(this, it, product)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            AddProdActivity.intent_requestCode -> data?.let {
                val prod: Product = it.getParcelableExtra("prod")
                viewModel.addProduct(prod)
                adapter.clearAll()
                Snackbar.make(main_fab, prod.name+" Added", Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .show()
            }
        }
    }

    override fun onRemoveClick(dialog: DialogFragment) {
        adapter.delItemFromList(viewModel.getProduct(), viewModel.getPosition())
        viewModel.delProd(viewModel.getProduct())
    }

    override fun onEditClick(dialog: DialogFragment) {
        showAddDialog(viewModel.getView() ,viewModel.getProduct())
    }
}
