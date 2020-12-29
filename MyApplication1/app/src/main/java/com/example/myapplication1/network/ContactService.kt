package com.example.myapplication1.network

import ContactResponse
import retrofit2.Call
import retrofit2.http.GET

interface ContactService {

    @GET("820b2285")
    fun contactList() : Call<ContactResponse>
}