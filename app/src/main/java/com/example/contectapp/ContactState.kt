package com.example.contectapp

import com.example.contectapp.Database.Contact

data class ContactState(
    val contact: List<Contact> = emptyList(),
    val Name: String = "",
    val phoneNumber: String = "",
    val isAddingContact: Boolean = false,

)
