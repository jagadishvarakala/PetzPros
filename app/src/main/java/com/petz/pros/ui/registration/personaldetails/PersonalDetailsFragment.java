package com.petz.pros.ui.registration.personaldetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.petz.pros.R;
import com.petz.pros.data.network.pojo.RegistrationRequest;
import com.petz.pros.databinding.FragmentPersonalDetailsBinding;
import com.petz.pros.ui.registration.OwnerRegistrationActivity;
import com.petz.pros.utils.CommonUtils;

public class PersonalDetailsFragment extends Fragment {

    private FragmentPersonalDetailsBinding personalDetailsBinding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        personalDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_personal_details, container, false);
        return personalDetailsBinding.getRoot();
    }

    public static PersonalDetailsFragment newInstance() {
        PersonalDetailsFragment f = new PersonalDetailsFragment();
        return f;
    }

    public boolean toValidatePersonalDetails(){
        boolean isValid = true;
        if(personalDetailsBinding.edtName.getText().toString().isEmpty()){
            personalDetailsBinding.edtName.setError("Enter Name");
            isValid = false;
        }else if(personalDetailsBinding.edtName.getText().toString().length() < 4){
            personalDetailsBinding.edtName.setError("Name must be more then 4 characters");
            isValid = false;
        }
        if(personalDetailsBinding.edtLastName.getText().toString().isEmpty()){
            personalDetailsBinding.edtLastName.setError("Enter Last Name");
            isValid = false;
        }
        if(personalDetailsBinding.edtEmail.getText().toString().isEmpty()){
            personalDetailsBinding.edtEmail.setError("Enter email");
            isValid = false;
        }else if(!CommonUtils.isValidEmail(personalDetailsBinding.edtEmail.getText())){
            personalDetailsBinding.edtEmail.setError("Please enter a valid email");
            isValid = false;
        }
        if(personalDetailsBinding.edtMobile.getText().toString().isEmpty()){
            personalDetailsBinding.edtMobile.setError("Enter Mobile Number");
            isValid = false;
        }else if(personalDetailsBinding.edtMobile.getText().toString().length() < 10){
            personalDetailsBinding.edtMobile.setError("Please enter valid mobile number");
            isValid = false;
        }
        if(personalDetailsBinding.edtPassword.getText().toString().isEmpty()){
            personalDetailsBinding.edtPassword.setError("Enter Password");
            isValid = false;
        }else if(!CommonUtils.validatePassword(personalDetailsBinding.edtPassword.getText().toString())){
            personalDetailsBinding.edtPassword.setError("Password must contain at least four characters, including uppercase, lowercase letters and numbers");
            isValid = false;
        }
        if(personalDetailsBinding.edtConfirmPassword.getText().toString().isEmpty()){
            personalDetailsBinding.edtConfirmPassword.setError("Enter Confirm Password");
            isValid = false;
        }else if(!personalDetailsBinding.edtPassword.getText().toString().equals(personalDetailsBinding.edtConfirmPassword.getText().toString())){
            personalDetailsBinding.edtConfirmPassword.setError("password and confirm password doesn't match");
            isValid = false;
        }

        if(!isValid){
            return isValid;
        }
        if(getActivity()!= null){
            if(getActivity() instanceof OwnerRegistrationActivity){
                ((OwnerRegistrationActivity)getActivity()).registrationRequest.setFirstName(personalDetailsBinding.edtName.getText().toString());
                ((OwnerRegistrationActivity)getActivity()).registrationRequest.setLastName(personalDetailsBinding.edtLastName.getText().toString());
                ((OwnerRegistrationActivity)getActivity()).registrationRequest.setEmailId(personalDetailsBinding.edtEmail.getText().toString());
                ((OwnerRegistrationActivity)getActivity()).registrationRequest.setPhone(personalDetailsBinding.edtMobile.getText().toString());
                ((OwnerRegistrationActivity)getActivity()).registrationRequest.setPassword(personalDetailsBinding.edtPassword.getText().toString());
            }
        }
        return isValid;
    }
}
