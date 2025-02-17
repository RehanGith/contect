package com.example.contectapp

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contectapp.Database.Contact
import com.example.contectapp.Database.ContactDatabase
import com.example.contectapp.Repository.ContactRepository
import com.example.contectapp.ViewModel.ContactViewModel
import com.example.contectapp.ViewModel.ViewModelFactory
import com.example.contectapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() , ContactAdapter.OnContactClickListener{
    private lateinit var binding: ActivityMainBinding
    private lateinit var contactViewModel : ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeViewModel()
        setupRecyclerView()
        binding.btAdd.setOnClickListener {
            displayAddContactDialog()
        }

    }

    private fun initializeViewModel() {
        val contactRepo = ContactRepository(ContactDatabase(this))
        val factory = ViewModelFactory(application, contactRepo)
        contactViewModel = ViewModelProvider(this, factory)[ContactViewModel::class.java]

    }
    private fun updateUI(contactList: List<Contact>?) {
        if(contactList != null) {
                binding.rvContactsList.visibility = View.VISIBLE
            Log.d("MainActivity", "Contact list size: ${contactList.size}")
        } else {
            Log.d("MainActivity", "Contact list is null")
        }
    }

    private fun setupRecyclerView() {
        val contactAdapter = ContactAdapter(this)
        binding.rvContactsList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = contactAdapter
            setHasFixedSize(true)
        }
        contactViewModel.getContacts().observe(this) { note ->
            contactAdapter.differ.submitList(note)
            updateUI(note)
        }
    }
    override fun onDeleteClick(contact: Contact) {
        showDialogForDelete(contact)
    }
    private fun showDialogForDelete(contact: Contact) {
        AlertDialog.Builder(this)
            .setTitle("Delete Contact")
            .setMessage("Are you sure you want to delete this contact?")
            .setPositiveButton("yes") { dialog, _ ->
                contactViewModel.deleteContact(contact)
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()

            }.create().show()


    }
    @SuppressLint("InflateParams")
    private fun displayAddContactDialog() {
        val dialogView = LayoutInflater.from(this).inflate(
            R.layout.dialog_add_contect,
            null
        )

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(true)
            .create()


        dialogView.findViewById<Button>(R.id.btnAddContact).setOnClickListener {
            val name = dialogView.findViewById<EditText>(R.id.etFirstName).text.toString()
            val phone = dialogView.findViewById<EditText>(R.id.etPhoneNumber).text.toString()
            if (name.isNotEmpty() && phone.isNotEmpty()) {
                val contact = Contact(name , phone)
                contactViewModel.insertContact(contact)
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Name and Phone cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }
}