package com.xMitternachtx.ecwid.viewmodels

import android.view.View
import androidx.lifecycle.*
import com.xMitternachtx.ecwid.model.Product
import com.xMitternachtx.ecwid.room.ProductRepository
import javax.inject.Inject

class MainActivityViewModel @Inject
constructor(
        private val productRepository: ProductRepository
) : ViewModel() {
    val products: LiveData<List<Product>>?
    private var name: MutableLiveData<Product> = MutableLiveData()
    private var pos: MutableLiveData<Int> = MutableLiveData()
    private var vView: MutableLiveData<View> = MutableLiveData()

    init {
        products =  productRepository.getProducts()
    }


    fun addProducts(prods: List<Product>){
        productRepository.insertProds(prods)
    }

    fun addProduct(prod: Product){
        productRepository.insertProd(prod)
    }

    fun setProduct(product: Product, adapterPosition: Int){
        name.value = product
        pos.value = adapterPosition
    }

    fun getProduct(): Product{
        return name.value!!
    }

    fun getPosition(): Int{
        return pos.value!!
    }

    fun setView(view: View){
        vView.value = view
    }

    fun getView(): View{
        return vView.value!!
    }

    fun delProd(prod: Product){
        productRepository.deleteProduct(prod)
    }

    fun refresh(user: String) {
//        productRepository.deleteProduct(user)
    }
}