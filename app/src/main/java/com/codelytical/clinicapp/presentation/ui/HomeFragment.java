package com.codelytical.clinicapp.presentation.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.codelytical.clinicapp.R;
import com.codelytical.clinicapp.databinding.FragmentHomeBinding;
import com.codelytical.clinicapp.presentation.adapter.DoctorAdapter;
import com.codelytical.clinicapp.presentation.viewmodel.HomeViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {

    FragmentHomeBinding homeBinding;
    private DoctorAdapter recyclerViewAdapter;
    private ProgressDialog progressDialog;
    private HomeViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressDialog = new ProgressDialog(requireContext());

        homeBinding = FragmentHomeBinding.bind(view);

        initRecyclerView();
        initViewModel();
    }

    private void initViewModel() {
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Loading.....");
        progressDialog.setCancelable(false);
        progressDialog.show();

        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        viewModel.getLiveData().observe(getViewLifecycleOwner(), recyclerData -> {
            if (recyclerData != null) {
                progressDialog.dismiss();
                recyclerViewAdapter.setListDataItems(recyclerData);
                recyclerViewAdapter.notifyDataSetChanged();
            } else {
                progressDialog.dismiss();
                Toast.makeText(requireContext(), "Error in getting data", Toast.LENGTH_SHORT).show();
                ;
            }
        });
        viewModel.getAllDoctors();
    }

    private void initRecyclerView() {
        homeBinding.doctorRV.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerViewAdapter = new DoctorAdapter();
        homeBinding.doctorRV.setAdapter(recyclerViewAdapter);
    }



}