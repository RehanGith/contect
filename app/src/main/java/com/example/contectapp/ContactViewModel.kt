package com.example.contectapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contectapp.Database.Contact
import com.example.contectapp.Database.ContactDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class ContactViewModel(private val dao: ContactDao) :ViewModel() {
    private val _state = MutableStateFlow(ContactState())
    private val _contacts = dao.getAllContacts()



    val state = combine(_state, _contacts) { state, contacts ->
        state.copy(
            contact = contacts
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ContactState())
    fun onEvent(event: ContactEvents) {
        when (event) {
            is ContactEvents.DeleteContact -> {
                viewModelScope.launch {
                    dao.delete(event.contact)
                }
            }
            ContactEvents.hideDialog -> {
                _state.update {
                    it.copy(
                        isAddingContact = false
                    )
                }
            }
            ContactEvents.saveContact -> {
                val name = state.value.name
                val phoneNumber = state.value.phoneNumber
                if (name.isBlank() || phoneNumber.isBlank()) {
                    return
                }
                val contact = Contact(
                    name = name, phone = phoneNumber)
                _state.update {
                    it.copy(
                        isAddingContact = false,
                        name = "",
                        phoneNumber = ""
                    )
                }
            }
            is ContactEvents.setName -> {
                _state.update { it.copy(
                    name = event.name
                )
                }
            }
            is ContactEvents.setPhoneNumber -> {
                _state.update {
                    it.copy(
                        phoneNumber = event.phoneNumber
                    )
                }
            }
            ContactEvents.showDialog -> {
                _state.update {
                    it.copy(
                        isAddingContact = true
                    )
                }
            }
        }
    }


}