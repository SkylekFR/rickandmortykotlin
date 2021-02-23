package com.emilien.rickandmortykotlin.data.database

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.emilien.rickandmortykotlin.entities.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    suspend fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    suspend fun loadAllByIds(userIds: IntArray): List<User>

    //@OnConflictStrategy(/*REPLACE*/)
    @Insert
    suspend fun insertAll(user: User)

    @Delete
    suspend fun delete(user: User)
}