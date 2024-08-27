package com.adrian.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adrian.data.entity.Credential.Companion.TABLE_NAME

/**
 * Usually this isn't something meant to be locally stored.
 * But for the sake of pulling of this Quickly, we're doing
 * Login against a local database.
 */
@Entity(tableName = TABLE_NAME)
data class Credential(

    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,

    @ColumnInfo(name = USERNAME)
    val username: String? = null,

    @ColumnInfo(name = PASSWORD)
    val password: String? = null,

    @ColumnInfo(name = USER_ID)
    val userId: Long? = null,
) {
    companion object {
        const val TABLE_NAME = "Credential"
        const val USER_ID = "userId"
        const val USERNAME = "username"
        const val PASSWORD = "password"
    }
}