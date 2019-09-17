package com.xMitternachtx.ecwid.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.xMitternachtx.ecwid.model.Product


@Dao
interface ProductDao {

    @Query("SELECT * FROM products ORDER BY id DESC")
    fun queryProducts(): LiveData<List<Product>>

    @Query("SELECT * FROM products WHERE name = :name")
    fun queryProduct(name: String): LiveData<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProd(product: Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProds(product: List<Product>)

    @Delete
    fun deleteProduct(model: Product)
}