package com.xMitternachtx.ecwid.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
        tableName = "products"
)
data class Product(
        @PrimaryKey
        val id: Long,
        val name: String?,
        val cost: String?,
        val description: String?,
        val image: String?
)