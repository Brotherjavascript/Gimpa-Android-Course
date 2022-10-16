package com.codelytical.clinicapp.data.api;

import com.codelytical.clinicapp.data.model.DoctorListModel;
import com.codelytical.clinicapp.data.model.LoginRequest;
import com.codelytical.clinicapp.data.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GimpaApiService {

    @GET("/api/users")
    Call<DoctorListModel> getDoctorList();

    @POST("/api/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);


}
