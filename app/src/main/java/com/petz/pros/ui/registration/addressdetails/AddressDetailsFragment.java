package com.petz.pros.ui.registration.addressdetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.petz.pros.R;
import com.petz.pros.databinding.FragmentAddressDetailsBinding;
import com.petz.pros.ui.registration.OwnerRegistrationActivity;
import com.petz.pros.utils.CommonUtils;

public class AddressDetailsFragment extends Fragment {

    private FragmentAddressDetailsBinding addressDetailsBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        addressDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_address_details, container, false);
        return addressDetailsBinding.getRoot();
    }

    public static AddressDetailsFragment newInstance() {
        AddressDetailsFragment f = new AddressDetailsFragment();
        return f;
    }

    public boolean toValidateAddressDetails(){
        boolean isValid = true;
        if(addressDetailsBinding.edtAddress.getText().toString().isEmpty()){
            addressDetailsBinding.edtAddress.setError("Enter Address");
            isValid = false;
        }
        if(addressDetailsBinding.edtCity.getText().toString().isEmpty()){
            addressDetailsBinding.edtCity.setError("Enter City");
            isValid = false;
        }
        if(addressDetailsBinding.edtCountry.getText().toString().isEmpty()){
            addressDetailsBinding.edtCountry.setError("Enter Country");
            isValid = false;
        }
        if(addressDetailsBinding.edtState.getText().toString().isEmpty()){
            addressDetailsBinding.edtState.setError("Enter State");
            isValid = false;
        }
        if(addressDetailsBinding.edtZipCode.getText().toString().isEmpty()){
            addressDetailsBinding.edtZipCode.setError("Enter ZipCode");
            isValid = false;
        }else if(addressDetailsBinding.edtZipCode.getText().toString().length() < 6){
            addressDetailsBinding.edtZipCode.setError("ZipCode must be 6 characters");
            isValid = false;
        }
        if(!isValid){
            return isValid;
        }
        if(getActivity()!= null){
            if(getActivity() instanceof OwnerRegistrationActivity){
                ((OwnerRegistrationActivity)getActivity()).registrationRequest.setAddress(addressDetailsBinding.edtAddress.getText().toString());
                ((OwnerRegistrationActivity)getActivity()).registrationRequest.setCity(addressDetailsBinding.edtCity.getText().toString());
                ((OwnerRegistrationActivity)getActivity()).registrationRequest.setCountry(addressDetailsBinding.edtCountry.getText().toString());
                ((OwnerRegistrationActivity)getActivity()).registrationRequest.setState(addressDetailsBinding.edtState.getText().toString());
                ((OwnerRegistrationActivity)getActivity()).registrationRequest.setZipCode(Integer.parseInt(addressDetailsBinding.edtZipCode.getText().toString()));
            }
        }
        return isValid;
    }
}
