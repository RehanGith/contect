package com.example.contectapp.ViewModel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contectapp.Database.Contact
import com.example.contectapp.Database.ContactDao
import kotlinx.coroutines.launch


class ContactViewModel(private val app: Application, private val dao: ContactDao): AndroidViewModel(app) {



}