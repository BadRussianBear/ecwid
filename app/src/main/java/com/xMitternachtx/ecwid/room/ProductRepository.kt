package com.xMitternachtx.ecwid.room

import androidx.lifecycle.LiveData
import com.xMitternachtx.ecwid.model.Product
import javax.inject.Inject

class ProductRepository @Inject constructor(var productDao: ProductDao) {

    fun deleteProduct(product: Product) {
        productDao.deleteProduct(product)
//        doAsync { productDao.deleteProduct(product) }
    }

    fun getProducts(): LiveData<List<Product>> {
        return productDao.queryProducts()
    }

    fun getProduct(name: String): LiveData<Product>{
        return productDao.queryProduct(name)
    }

    fun getSingleProduct(name: String): LiveData<Product> {
        return productDao.queryProduct(name)
    }

    fun insertProds(product: List<Product>){
        return productDao.insertProds(product)
    }
}