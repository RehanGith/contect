package com.example.contectapp.ViewModel

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.example.contectapp.Repository.ContactRepository

class ViewModelFactory(val app: Application, private val repo: ContactRepository):
    ViewModelProvider.Factory {
        override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        return ContactViewModel(app, repo) as T
    }

}