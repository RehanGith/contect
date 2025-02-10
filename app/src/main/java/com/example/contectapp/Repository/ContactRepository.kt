package com.example.contectapp.Repository

import com.example.contectapp.Database.Contact
import com.example.contectapp.Database.ContactDatabase

class ContactRepository( private  val db: ContactDatabase) {
    suspend fun insertContact(contact: Contact) = db.getContactDao().upsert(contact)
    suspend fun deleteContact(contact: Contact) = db.getContactDao().delete(contact)
    fun displayContacts() = db.getContactDao().getAllContacts()
}