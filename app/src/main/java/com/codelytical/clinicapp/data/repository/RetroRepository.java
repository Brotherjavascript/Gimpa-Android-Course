package com.codelytical.clinicapp.data.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.codelytical.clinicapp.data.api.GimpaApiService;
import com.codelytical.clinicapp.data.model.DoctorListModel;
import com.codelytical.clinicapp.data.model.DoctorModel;
import com.codelytical.clinicapp.data.model.LoginRequest;
import com.codelytical.clinicapp.data.model.LoginResponse;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetroRepository {

    private final GimpaApiService gimpaApiService;

    public RetroRepository(GimpaApiService gimpaApiService) {
        this.gimpaApiService = gimpaApiService;
    }

    public void signIn(LoginRequest loginRequest, MutableLiveData<LoginResponse> loginResponse) {
        Call<LoginResponse> call  = gimpaApiService.login(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    loginResponse.postValue(response.body());
                } else {
                    loginResponse.postValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                loginResponse.postValue(null);
                Log.d("TAG", "onFailure: FAILURE"+t.getLocalizedMessage());
            }
        });
    }

    public void getDoctorCall(MutableLiveData<List<DoctorModel>> liveData) {
        Call<DoctorListModel> call  = gimpaApiService.getDoctorList();
        call.enqueue(new Callback<DoctorListModel>() {
            @Override
            public void onResponse(@NonNull Call<DoctorListModel> call, @NonNull Response<DoctorListModel> response) {
                if(response.isSuccessful()) {
                    liveData.postValue(Objects.requireNonNull(response.body()).getData());
                } else {
                    liveData.postValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<DoctorListModel> call, @NonNull Throwable t) {
                liveData.postValue(null);
                Log.d("TAG", "onFailure: FAILURE"+t.getLocalizedMessage());
            }
        });


    }
}
