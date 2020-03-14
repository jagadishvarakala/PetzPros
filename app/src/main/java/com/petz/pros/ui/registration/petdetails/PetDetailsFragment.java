package com.petz.pros.ui.registration.petdetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.petz.pros.R;
import com.petz.pros.data.network.pojo.RegistrationRequest;
import com.petz.pros.databinding.FragmentPetDetailsBinding;
import com.petz.pros.ui.registration.OwnerRegistrationActivity;

public class PetDetailsFragment extends Fragment {

    private FragmentPetDetailsBinding petDetailsBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        petDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pet_details, container, false);
        return petDetailsBinding.getRoot();
    }

    public static PetDetailsFragment newInstance() {
        PetDetailsFragment f = new PetDetailsFragment();
        return f;
    }

    public boolean toValidatePetDetails(){
        boolean isValid = true;
        RadioButton selectedTypeRadioButton;
        boolean isPetFriendly = false;
        if(petDetailsBinding.petType.getCheckedRadioButtonId() == -1){
            Toast.makeText(getContext(),"Select Pet Type",Toast.LENGTH_LONG).show();
            isValid = false;
            return isValid;
        }else {
            int selectedId = petDetailsBinding.petType.getCheckedRadioButtonId();
            selectedTypeRadioButton = petDetailsBinding.getRoot().findViewById(selectedId);
        }
        if(petDetailsBinding.edtPetName.getText().toString().isEmpty()){
            petDetailsBinding.edtPetName.setError("Enter Pet Name");
            isValid = false;
            return isValid;
        }
        if(petDetailsBinding.edtPetBreed.getText().toString().isEmpty()){
            petDetailsBinding.edtPetBreed.setError("Enter Pet Breed");
            isValid = false;
            return isValid;
        }
        if(petDetailsBinding.edtPetAge.getText().toString().isEmpty()){
            petDetailsBinding.edtPetAge.setError("Enter Pet Age");
            isValid = false;
            return isValid;
        }
        if(petDetailsBinding.petFriendly.getCheckedRadioButtonId() == -1){
            Toast.makeText(getContext(),"Select Pet friendly",Toast.LENGTH_LONG).show();
            isValid = false;
            return isValid;
        }else{
            int selectedId = petDetailsBinding.petType.getCheckedRadioButtonId();
            isPetFriendly = selectedId == 1;
        }


        if(getActivity()!= null){
            if(getActivity() instanceof OwnerRegistrationActivity){
                RegistrationRequest.PetDetails petDetails = new RegistrationRequest.PetDetails();
                petDetails.setType(selectedTypeRadioButton.getText().toString());
                petDetails.setName(petDetailsBinding.edtPetName.getText().toString());
                petDetails.setBreed(petDetailsBinding.edtPetBreed.getText().toString());
                petDetails.setAgeInYears(Integer.parseInt(petDetailsBinding.edtPetAge.getText().toString()));
                petDetails.setIsFriendly(isPetFriendly);
                petDetails.setAbout(petDetailsBinding.edtPetAbout.getText().toString());
                ((OwnerRegistrationActivity)getActivity()).registrationRequest.setPetDetails(petDetails);
            }
        }
        return isValid;
    }
}
