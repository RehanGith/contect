package com.example.contectapp.ViewModel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contectapp.Database.Contact
import com.example.contectapp.Database.ContactDao
import com.example.contectapp.Repository.ContactRepository
import kotlinx.coroutines.launch


class ContactViewModel(private val app: Application, private val repo: ContactRepository): AndroidViewModel(app) {
    fun insertContact(contact: Contact) = viewModelScope.launch {
        repo.insertContact(contact)
    }
    fun deleteContact(contact: Contact) = viewModelScope.launch {
        repo.deleteContact(contact)
    }
    fun getContacts() = repo.displayContacts()

}