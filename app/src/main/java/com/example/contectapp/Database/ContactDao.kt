package com.example.contectapp.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Upsert
    suspend fun upsert(contact: Contact)
    @Delete
    suspend fun delete(contact: Contact)
    @Query("SELECT * FROM contact")
    fun getAllContacts(): Flow<List<Contact>>

}