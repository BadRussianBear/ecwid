package com.xMitternachtx.ecwid.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xMitternachtx.ecwid.model.Product

@Database(entities = [(Product::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}