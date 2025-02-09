package com.example.contectapp.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface ContactDao {
    @Upsert
    fun upsert(contact: Contact)
    @Delete
    fun delete(contact: Contact)
    @Query("SELECT * FROM contact")
    fun getAllContacts(): LiveData<List<Contact>>

}