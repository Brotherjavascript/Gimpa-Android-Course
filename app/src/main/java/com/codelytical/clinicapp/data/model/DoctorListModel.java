package com.codelytical.clinicapp.data.model;

import java.util.List;

public class DoctorListModel {
    private List<DoctorModel> data;

    public DoctorListModel(List<DoctorModel> data) {
        this.data = data;
    }

    public List<DoctorModel> getData() {
        return data;
    }

    public void setData(List<DoctorModel> data) {
        this.data = data;
    }
}
