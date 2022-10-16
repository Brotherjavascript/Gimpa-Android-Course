package com.codelytical.clinicapp.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.codelytical.clinicapp.data.api.GimpaApiService;
import com.codelytical.clinicapp.data.model.LoginRequest;
import com.codelytical.clinicapp.data.model.LoginResponse;
import com.codelytical.clinicapp.data.repository.RetroRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoginViewModel extends ViewModel {

    MutableLiveData<LoginResponse> loginResponseMutableLiveData;

    @Inject
    GimpaApiService gimpaApiService;

    @Inject
    public LoginViewModel() {
        loginResponseMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<LoginResponse> getLoginResponseMutableLiveData() {
        return loginResponseMutableLiveData;
    }

    public void signInUser(LoginRequest loginRequest) {
        RetroRepository repository =new RetroRepository(gimpaApiService);
        repository.signIn(loginRequest, loginResponseMutableLiveData);
    }
}
