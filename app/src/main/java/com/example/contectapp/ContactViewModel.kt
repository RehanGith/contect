package com.example.contectapp


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contectapp.Database.Contact
import com.example.contectapp.Database.ContactDao
import kotlinx.coroutines.launch


class ContactViewModel(private val dao: ContactDao) :ViewModel() {
    private val _state = MutableLiveData(ContactState())
    val contacts = dao.getAllContacts()

    val state: LiveData<ContactState>
        get() = _state

    fun onEvent(event: ContactEvents) {
        when (event) {
            is ContactEvents.DeleteContact -> {
                viewModelScope.launch {
                    dao.delete(event.contact)
                }
            }
            ContactEvents.hideDialog -> {
                _state.value = _state.value?.copy(
                    isAddingContact = false
                )
            }
            ContactEvents.saveContact -> {
                val name = state.value?.name
                val phoneNumber = state.value?.phoneNumber
                if(name != null && phoneNumber != null) {
                    val contact = Contact(
                        name = name,
                        phone = phoneNumber
                    )
                    viewModelScope.launch {
                        dao.upsert(contact)
                    }
                }
                _state.value = _state.value?.copy(
                    isAddingContact = false,
                    name = "",
                    phoneNumber = ""
                )
            }
            is ContactEvents.setName -> {
                _state.value = _state.value?.copy(
                    name = event.name
                )
            }
            is ContactEvents.setPhoneNumber -> {
                _state.value = _state.value?.copy(
                    phoneNumber = event.phoneNumber
                )
            }
            ContactEvents.showDialog -> {
                _state.value = _state.value?.copy(
                    isAddingContact = true
                )
            }
        }
    }


}