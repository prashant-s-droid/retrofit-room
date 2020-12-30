package com.example.turecallerjavademo.network;

import com.example.turecallerjavademo.model.Contact;
import com.example.turecallerjavademo.model.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://labs.codefiretechnologies.com/";
    @GET("contact_info.php")
    Call<UserResponse> getstatus();
}
