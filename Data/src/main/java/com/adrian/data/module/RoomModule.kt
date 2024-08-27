package com.adrian.data.module

import android.content.Context
import androidx.room.Room
import com.adrian.data.dao.AuthDao
import com.adrian.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun database(@ApplicationContext applicationContext: Context): AppDatabase =
        Room.databaseBuilder(
            context = applicationContext,
            klass = AppDatabase::class.java,
            name = "EldarDatabase"
        ).build()

    @Singleton
    @Provides
    fun authDao(database: AppDatabase): AuthDao =
        database.authDao()
}