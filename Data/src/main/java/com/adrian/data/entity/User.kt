package com.adrian.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adrian.data.entity.User.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class User(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    val id: Long? = null,

    @ColumnInfo(name = NAME)
    val name: String? = null,

    @ColumnInfo(name = LAST_NAME)
    val lastName: String? = null

) {
    companion object {
        const val TABLE_NAME = "User"
        const val ID = "Id"
        const val NAME = "Name"
        const val LAST_NAME = "Last_Name"
    }
}