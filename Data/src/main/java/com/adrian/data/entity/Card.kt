package com.adrian.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Card.TABLE_NAME)
data class Card(

    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,

    @ColumnInfo(name = USER_ID)
    val userId: String? = null,

    @ColumnInfo(name = CARD_NUMBER)
    val number: String? = null,

    @ColumnInfo(name = SECURITY_CODE)
    val cvv: String? = null,

    @ColumnInfo(name = EXPIRE_DATE)
    val expiry: String? = null

) {
    companion object {
        const val TABLE_NAME = "Cards"
        const val USER_ID = "F0"
        const val CARD_NUMBER = "F1"
        const val SECURITY_CODE = "F2"
        const val EXPIRE_DATE = "F3"
    }
}