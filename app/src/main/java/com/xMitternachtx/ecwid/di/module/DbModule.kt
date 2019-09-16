package com.xMitternachtx.ecwid.di.module

import android.app.Application
import androidx.annotation.NonNull
import androidx.room.Room
import com.xMitternachtx.ecwid.room.AppDatabase
import com.xMitternachtx.ecwid.room.MIGRATION_1_2
import com.xMitternachtx.ecwid.room.ProductDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Network Module for api
 */

@Module
class DbModule {

    @Provides
    @Singleton
    fun provideDatabase(@NonNull application: Application): AppDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java,
            "ecwid_products1.db")
            .addMigrations(MIGRATION_1_2)
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideProductDao(@NonNull database: AppDatabase): ProductDao {
        return database.productDao()
    }
}
