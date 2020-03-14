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
import com.petz.pros.ui.registration.caretacker.CareTackerRegistrationActivity;
import com.petz.pros.utils.CommonUtils;

public class AddressDetailsFragment extends Fragment {

    private FragmentAddressDetailsBinding addressDetailsBinding;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        addressDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_address_details, container, false);
        return addressDetailsBinding.getRoot();
    }

    public static AddressDetailsFragment newInstance() {
        AddressDetailsFragment f = new AddressDetailsFragment();
        return f;
    }

    public boolean toValidateAddressDetails(){
        boolean isValid = false;
        if(addressDetailsBinding.edtAddress.getText().toString().isEmpty()){
            addressDetailsBinding.edtAddress.setError("Enter Address");
           return isValid ;
        }
        if(addressDetailsBinding.edtCity.getText().toString().isEmpty()){
            addressDetailsBinding.edtCity.setError("Enter City");
            return isValid ;
        }
        if(addressDetailsBinding.edtCountry.getText().toString().isEmpty()){
            addressDetailsBinding.edtCountry.setError("Enter Country");
            return isValid ;
        }
        if(addressDetailsBinding.edtState.getText().toString().isEmpty()){
            addressDetailsBinding.edtState.setError("Enter State");
            return isValid ;
        }
        if(addressDetailsBinding.edtZipCode.getText().toString().isEmpty()){
            addressDetailsBinding.edtZipCode.setError("Enter ZipCode");
            return isValid ;
        }else if(addressDetailsBinding.edtZipCode.getText().toString().length() < 5){
            addressDetailsBinding.edtZipCode.setError("ZipCode must be 5 characters");
            return isValid ;
        }

        if(getActivity()!= null){
            if(getActivity() instanceof OwnerRegistrationActivity){
                ((OwnerRegistrationActivity)getActivity()).registrationRequest.setAddress(addressDetailsBinding.edtAddress.getText().toString());
                ((OwnerRegistrationActivity)getActivity()).registrationRequest.setCity(addressDetailsBinding.edtCity.getText().toString());
                ((OwnerRegistrationActivity)getActivity()).registrationRequest.setCountry(addressDetailsBinding.edtCountry.getText().toString());
                ((OwnerRegistrationActivity)getActivity()).registrationRequest.setState(addressDetailsBinding.edtState.getText().toString());
                ((OwnerRegistrationActivity)getActivity()).registrationRequest.setZipCode(Integer.parseInt(addressDetailsBinding.edtZipCode.getText().toString()));
            }else if(getActivity() instanceof CareTackerRegistrationActivity){
                ((CareTackerRegistrationActivity)getActivity()).registrationRequest.setAddress(addressDetailsBinding.edtAddress.getText().toString());
                ((CareTackerRegistrationActivity)getActivity()).registrationRequest.setCity(addressDetailsBinding.edtCity.getText().toString());
                ((CareTackerRegistrationActivity)getActivity()).registrationRequest.setCountry(addressDetailsBinding.edtCountry.getText().toString());
                ((CareTackerRegistrationActivity)getActivity()).registrationRequest.setState(addressDetailsBinding.edtState.getText().toString());
                ((CareTackerRegistrationActivity)getActivity()).registrationRequest.setZipCode(Integer.parseInt(addressDetailsBinding.edtZipCode.getText().toString()));
            }
        }
        isValid = true;
        return isValid;
    }
}
