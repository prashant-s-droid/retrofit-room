package com.example.myapplication1.db.dao

import ContactData
import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

interface ContactDao {


    @Query("SELECT * FROM ContactData")
    fun getAll(): List<ContactData>

    @Insert
    fun insertAll(vararg contactData : ContactData)

    @Delete
    fun delete(contactData: ContactData)

    @Update
    fun updateTodo(vararg contactData: ContactData)

    @Query("SELECT * FROM ContactData WHERE contactNumber LIKE :number")
    fun findByNumber(number: String): LiveData<List<ContactData>>
}