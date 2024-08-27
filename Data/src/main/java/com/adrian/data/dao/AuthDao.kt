package com.adrian.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.adrian.data.entity.Credential
import com.adrian.data.entity.User

/**
 * This usually would be a Service, containing endpoints pointing to
 * Remote. For the sake of pulling this out quickly, temporally our AuthService
 * will work with a local Database.
 */
@Dao
interface AuthDao {

    // Usually, a Backend would handle this
    @Insert
    fun insertCredential(credential: Credential): Long

    // Usually, a Backend would handle this
    @Insert
    fun insertUser(user: User): Long

    @Query("SELECT COUNT(*) FROM Credential WHERE username = :username")
    fun isUsernameTaken(username: String): Int

    @Query("""
        SELECT u.Id, u.name 
        FROM User u 
        JOIN Credential c ON u.Id = c.userId 
        WHERE c.username = :username AND c.password = :password
        LIMIT 1
    """)
    fun signIn(username: String, password: String): User?

}