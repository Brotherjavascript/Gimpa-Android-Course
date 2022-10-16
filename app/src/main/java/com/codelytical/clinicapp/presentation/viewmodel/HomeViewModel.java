package com.codelytical.clinicapp.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.codelytical.clinicapp.data.api.GimpaApiService;
import com.codelytical.clinicapp.data.model.DoctorModel;
import com.codelytical.clinicapp.data.repository.RetroRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {

    MutableLiveData<List<DoctorModel>> liveData;

    @Inject
    GimpaApiService gimpaApiService;

    @Inject
    public HomeViewModel() {
        liveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<DoctorModel>> getLiveData() {
        return liveData;
    }

    public void getAllDoctors() {
        RetroRepository repository =new RetroRepository(gimpaApiService);
        repository.getDoctorCall(liveData);
    }
}
