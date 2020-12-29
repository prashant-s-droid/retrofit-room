package com.example.myapplication1

import ContactResponse
import ContactData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapplication1.db.AppDatabase
import com.example.myapplication1.network.ContactService
import com.example.myapplication1.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val buildService: ContactService = RetrofitClient.buildService(ContactService::class.java)
        val call: Call<ContactResponse> = buildService.contactList()
        call.enqueue(object : Callback<ContactResponse> {
            override fun onResponse(
                call: Call<ContactResponse>,
                response: Response<ContactResponse>
            ) {
                if (response.isSuccessful)
                    saveDataIntoRoomDatabase(response.body()!!.contactData)
                else Toast.makeText(this@MainActivity, "else", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ContactResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun saveDataIntoRoomDatabase(contactData: List<ContactData>) {
        val db = AppDatabase(this)

     /*   val contactEntityList: List<ContactEntity> = contacts.map {
            ContactEntity(
                contactName = it.contactName,
                contactType = it.contactType,
                note = it.note,
                contactNumber = it.contactNumber
            )
        }.toList()*/


        GlobalScope.launch(Dispatchers.Main) {

            try {
                db.contactDao().insertAll(contactData = contactData.toTypedArray())

                val list = db.contactDao().getAll()

                Log.d("TEst", "Print list : " + list.toString())
            } catch (e: Exception) {
                Log.d("TEst", e.message.toString())
            }
        }
    }
}