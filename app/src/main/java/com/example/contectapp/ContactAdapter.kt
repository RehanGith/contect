package com.example.contectapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.contectapp.Database.Contact
import com.example.contectapp.databinding.ContactBinding


class ContactAdapter: RecyclerView.Adapter<ContactAdapter.ViewHolder>() {
    class ViewHolder(binding: ContactBinding): RecyclerView.ViewHolder(binding.root) {
        val name = binding.tvName
        val phone = binding.tvPhoneNumber
        val delButton = binding.ivDelete
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ContactBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    private val differCallBack = object : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.id == newItem.id && newItem.name == oldItem.name && newItem.phone == oldItem.phone
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallBack)
    override fun getItemCount(): Int {
        return  differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentContact = differ.currentList[position]
        holder.name.text = currentContact.name
        holder.phone.text = currentContact.phone
    }

}