package com.example.contectapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contectapp.databinding.ContactBinding


class ContactAdapter(val context: Context, private val contacts: ArrayList<String>) : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {
    class ViewHolder(binding: ContactBinding): RecyclerView.ViewHolder(binding.root) {
        val name = binding.tvName
        val phone = binding.tvPhoneNumber
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ContactBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = contacts[position]
        holder.phone.text = "0123456789"
    }

}