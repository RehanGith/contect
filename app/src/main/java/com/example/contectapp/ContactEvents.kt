package com.example.contectapp

import com.example.contectapp.Database.Contact

sealed interface ContactEvents {
    object saveContact: ContactEvents
    data class setName(val name: String): ContactEvents
    data class setPhoneNumber(val phoneNumber: String): ContactEvents
    object showDialog: ContactEvents
    object hideDialog: ContactEvents
    data class DeleteContact(val contact: Contact): ContactEvents


}