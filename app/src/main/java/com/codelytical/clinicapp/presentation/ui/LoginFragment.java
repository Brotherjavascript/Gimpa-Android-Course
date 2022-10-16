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
import androidx.navigation.Navigation;

import com.codelytical.clinicapp.R;
import com.codelytical.clinicapp.data.model.LoginRequest;
import com.codelytical.clinicapp.databinding.FragmentLoginBinding;
import com.codelytical.clinicapp.presentation.viewmodel.LoginViewModel;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginFragment extends Fragment {

    FragmentLoginBinding loginBinding;
    private ProgressDialog progressDialog;
    private LoginViewModel loginViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        loginBinding = FragmentLoginBinding.inflate(inflater);

        return loginBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginBinding = FragmentLoginBinding.bind(view);

        progressDialog = new ProgressDialog(requireContext());

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        loginBinding.loginButton.setOnClickListener(view1 -> {

            String studentID = Objects.requireNonNull(loginBinding.loginUsername.getText()).toString();
            String pinCode = Objects.requireNonNull(loginBinding.loginPassword.getText()).toString();

            if (!studentID.isEmpty() || !pinCode.isEmpty()) {
                progressDialog.setTitle("Please wait");
                progressDialog.setMessage("Loading.....");
                progressDialog.setCancelable(false);
                progressDialog.show();

                LoginRequest loginRequest = new LoginRequest(loginBinding.loginUsername.getText().toString(), Objects.requireNonNull(loginBinding.loginPassword.getText()).toString());

                loginViewModel.signInUser(loginRequest);

                loginViewModel.getLoginResponseMutableLiveData().observe(getViewLifecycleOwner(), loginResponse -> {
                    if (loginResponse != null) {
                        progressDialog.dismiss();
                        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment);
                        emptyFields();
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(requireActivity(), "Error in getting data", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(requireActivity(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void emptyFields(){
        loginBinding.loginUsername.setText("");
        loginBinding.loginPassword.setText("");
    }
}