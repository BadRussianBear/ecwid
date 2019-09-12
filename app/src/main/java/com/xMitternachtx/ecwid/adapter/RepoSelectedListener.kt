package com.xMitternachtx.ecwid.adapter

import com.xMitternachtx.ecwid.model.Product
import java.util.ArrayList


/**
 * Interface for repo selected
 */
interface RepoSelectedListener {

    fun onRepoSelected(repo: Product, position: Int, data: ArrayList<Product>)
}