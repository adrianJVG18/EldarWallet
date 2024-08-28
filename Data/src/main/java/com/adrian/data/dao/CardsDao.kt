package com.adrian.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.adrian.data.entity.Card

@Dao
interface CardsDao {

    @Insert
    fun insertCard(card: Card): Card

    @Query("DELETE FROM ${Card.TABLE_NAME} WHERE id = :id")
    fun deleteCardById(id: Long)

    @Query("""
        SELECT id, ${Card.USER_ID}, ${Card.CARD_NUMBER}, ${Card.SECURITY_CODE}, ${Card.EXPIRE_DATE} 
        FROM ${Card.TABLE_NAME} 
        WHERE ${Card.USER_ID} = :userId
    """)
    fun getCardsByUserId(userId: String): List<Card>

}