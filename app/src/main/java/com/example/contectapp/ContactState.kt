package com.example.contectapp

import com.example.contectapp.Database.Contact

data class ContactState(
    val contact: List<Contact> = emptyList(),
    val name: String = "",
    val phoneNumber: String = "",
    val isAddingContact: Boolean = false,

)
