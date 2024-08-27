package com.adrian.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adrian.data.dao.AuthDao
import com.adrian.data.entity.Credential
import com.adrian.data.entity.User

@Database(
    entities = [
        Credential::class,
        User::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun authDao(): AuthDao
}