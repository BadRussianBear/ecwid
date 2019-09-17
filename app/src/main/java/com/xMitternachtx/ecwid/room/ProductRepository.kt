package com.xMitternachtx.ecwid.room

import androidx.lifecycle.LiveData
import com.xMitternachtx.ecwid.model.Product
import com.xMitternachtx.ecwid.util.doAsync
import java.lang.ref.WeakReference
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future
import javax.inject.Inject

class ProductRepository @Inject constructor(var productDao: ProductDao) {
    fun deleteProduct(product: Product) {
        //executor.execute { productDao.deleteProduct(product) }
        doAsync { productDao.deleteProduct(product) }
    }

    //Use LiveData no need for backThread
    fun getProducts(): LiveData<List<Product>> {
        return productDao.queryProducts()
    }

    //Use LiveData no need for backThread
    fun getProduct(name: String): LiveData<Product>{
        return productDao.queryProduct(name)
    }


    fun insertProds(product: List<Product>){
        doAsync {  productDao.insertProds(product) }
    }

    fun insertProd(product: Product){
        doAsync { productDao.insertProd(product) }
    }
}


