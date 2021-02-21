package com.emilien.rickandmortykotlin.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.emilien.rickandmortykotlin.entities.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    suspend fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    suspend fun loadAllByIds(userIds: IntArray): List<User>

    @Insert
    suspend fun insertAll(user: User)

    @Delete
    suspend fun delete(user: User)
}