package com.petz.pros.ui.registration.servicedetails;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.petz.pros.R;
import com.petz.pros.databinding.FragmentAddressDetailsBinding;
import com.petz.pros.databinding.FragmentServiceDetailsBinding;
import com.petz.pros.ui.registration.OwnerRegistrationActivity;
import com.petz.pros.ui.registration.caretacker.CareTackerRegistrationActivity;

import java.util.ArrayList;

public class ServiceDetailsFragment extends Fragment {

    private FragmentServiceDetailsBinding serviceDetailsBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        serviceDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_service_details, container, false);
        return serviceDetailsBinding.getRoot();
    }

    public static ServiceDetailsFragment newInstance() {
        ServiceDetailsFragment f = new ServiceDetailsFragment();
        return f;
    }

    public boolean toValidateServiceDetails(){
        boolean isValid = false;
        ArrayList<Integer> integers = new ArrayList<>();
        if(serviceDetailsBinding.dogWalkingCheckBox.isChecked()){
            integers.add(1);
            isValid = true;
        }
        if(serviceDetailsBinding.dogBoardingCheckBox.isChecked()){
            integers.add(2);
            isValid = true;
        }
        if(serviceDetailsBinding.dogSittingCheckBox.isChecked()){
            integers.add(3);
            isValid = true;
        }
        if(serviceDetailsBinding.dogDayCareCheckBox.isChecked()){
            integers.add(4);
            isValid = true;
        }
        if(serviceDetailsBinding.catBoardingCheckBox.isChecked()){
            integers.add(5);
            isValid = true;
        }
        if(serviceDetailsBinding.catSittingCheckBox.isChecked()){
            integers.add(6);
            isValid = true;
        }
        if(serviceDetailsBinding.dropVisitsCheckBox.isChecked()){
            integers.add(7);
            isValid = true;
        }
        if(TextUtils.isEmpty(serviceDetailsBinding.edtChargePerHour.getText().toString())){
            serviceDetailsBinding.edtChargePerHour.setError("Please enter Charge");
            isValid = false;
        }
        if(!isValid){
            Toast.makeText(getContext(),"Select service type",Toast.LENGTH_LONG).show();
            return isValid;
        }
        if(getActivity()!= null){
            if(getActivity() instanceof CareTackerRegistrationActivity){
                ((CareTackerRegistrationActivity)getActivity()).registrationRequest.setServiceIdList(integers);
                ((CareTackerRegistrationActivity)getActivity()).registrationRequest.setChargePerHour(Integer.parseInt(serviceDetailsBinding.edtChargePerHour.getText().toString()));
            }
        }
        return isValid;
    }
}
