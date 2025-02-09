package com.example.contectapp.Database

import android.content.Context
import androidx.room.Database

import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase : RoomDatabase(){
    abstract fun getContactDao():ContactDao
    companion object{
        private var instance: ContactDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context): ContactDatabase  =
            Room.databaseBuilder(
                context.applicationContext,
                ContactDatabase::class.java,
                "contact_db"
            ).build()
    }
}