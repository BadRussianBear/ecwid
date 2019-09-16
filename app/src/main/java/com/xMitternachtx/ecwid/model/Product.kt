package com.xMitternachtx.ecwid.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(
        tableName = "products"
)
@Parcelize
data class Product(
        @PrimaryKey(autoGenerate = true)
        val id: Long,
        val name: String?,
        val cost: String?,
        val description: String?,
        val image: String?
) : Parcelable