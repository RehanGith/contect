package com.example.contectapp

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contectapp.Database.Contact
import com.example.contectapp.Database.ContactDatabase
import com.example.contectapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db : ContactDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding.rvContactsList.adapter = ContactAdapter(this, ContactViewModel.contacts)
        binding.rvContactsList.layoutManager = LinearLayoutManager(this)
        binding.rvContactsList.setHasFixedSize(true)
        binding.rvContactsList.setItemViewCacheSize(10)
        binding.btAdd.setOnClickListener {
            displayAddContactDialog()
        }

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
                Toast.makeText(this, "Name $name and Phone $phone", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Name and Phone cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }
}