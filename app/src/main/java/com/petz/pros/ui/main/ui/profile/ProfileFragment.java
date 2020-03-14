package com.petz.pros.ui.main.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.google.firebase.iid.FirebaseInstanceId;
import com.petz.pros.R;
import com.petz.pros.data.network.pojo.RegistrationRequest;
import com.petz.pros.databinding.FragmentProfileBinding;
import com.petz.pros.ui.base.BaseFragment;
import com.petz.pros.utils.CommonUtils;

import javax.inject.Inject;

import okhttp3.ResponseBody;


public class ProfileFragment extends BaseFragment implements ProfileMvpView {


    @Inject
    ProfileMvpPresenter<ProfileMvpView> profileMvpPresenter;
    private FragmentProfileBinding profileBinding;
    private RegistrationRequest registrationRequest;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        getActivityComponent().inject(this);
        profileMvpPresenter.onAttach(ProfileFragment.this);
        registrationRequest = new RegistrationRequest();
        registrationRequest.setUserType(profileMvpPresenter.getUserType());
        registrationRequest.setId(profileMvpPresenter.getUserId());
        registrationRequest.setFCMDeviceId(FirebaseInstanceId.getInstance().getToken());
        return profileBinding.getRoot();
    }

    @Override
    protected void setUp(View view) {
        profileBinding.setProfile(profileMvpPresenter.getProfileDetails());

        profileBinding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toValidatePetDetails()){
                    profileMvpPresenter.petOwnerProfileUpdate(registrationRequest);
                }
            }
        });

    }

    @Override
    public void onSuccessProfileUpdate(RegistrationRequest body) {
        showMessage("Profile updated successfully");
    }

    public boolean toValidatePetDetails() {
        boolean isValid = false;
        if (profileBinding.edtName.getText().toString().isEmpty()) {
            profileBinding.edtName.setError("Enter Name");
            return isValid;
        } else if (profileBinding.edtName.getText().toString().length() < 4) {
            profileBinding.edtName.setError("Name must be more then 4 characters");
            return isValid;
        }
        if (profileBinding.edtLastName.getText().toString().isEmpty()) {
            profileBinding.edtLastName.setError("Enter Last Name");
            return isValid;
        }
        if (profileBinding.edtEmail.getText().toString().isEmpty()) {
            profileBinding.edtEmail.setError("Enter email");
            return isValid;
        } else if (!CommonUtils.isValidEmail(profileBinding.edtEmail.getText())) {
            profileBinding.edtEmail.setError("Please enter a valid email");
            return isValid;
        }
        if (profileBinding.edtMobile.getText().toString().isEmpty()) {
            profileBinding.edtMobile.setError("Enter Mobile Number");
            return isValid;
        } else if (profileBinding.edtMobile.getText().toString().length() < 10) {
            profileBinding.edtMobile.setError("Please enter valid mobile number");
            return isValid;
        }
        if(profileBinding.edtPassword.getText().toString().isEmpty()){
            profileBinding.edtPassword.setError("Enter Password");
            return isValid ;
        }else if(!CommonUtils.validatePassword(profileBinding.edtPassword.getText().toString())){
            profileBinding.edtPassword.setError("Password must contain at least four characters, including uppercase, lowercase letters and numbers");
            return isValid ;
        }
        if(profileBinding.edtConfirmPassword.getText().toString().isEmpty()){
            profileBinding.edtConfirmPassword.setError("Enter Confirm Password");
            return isValid ;
        }else if(!profileBinding.edtPassword.getText().toString().equals(profileBinding.edtConfirmPassword.getText().toString())){
            profileBinding.edtConfirmPassword.setError("password and confirm password doesn't match");
            return isValid;
        }

        if (getActivity() != null) {
            registrationRequest.setFirstName(profileBinding.edtName.getText().toString());
            registrationRequest.setLastName(profileBinding.edtLastName.getText().toString());
            registrationRequest.setEmailId(profileBinding.edtEmail.getText().toString());
            registrationRequest.setPhone(profileBinding.edtMobile.getText().toString());
            registrationRequest.setPassword(profileBinding.edtPassword.getText().toString());
        }

        if (profileBinding.edtAddress.getText().toString().isEmpty()) {
            profileBinding.edtAddress.setError("Enter Address");
            return isValid;
        }
        if (profileBinding.edtCity.getText().toString().isEmpty()) {
            profileBinding.edtCity.setError("Enter City");
            return isValid;
        }
        if (profileBinding.edtCountry.getText().toString().isEmpty()) {
            profileBinding.edtCountry.setError("Enter Country");
            return isValid;
        }
        if (profileBinding.edtState.getText().toString().isEmpty()) {
            profileBinding.edtState.setError("Enter State");
            return isValid;
        }
        if (profileBinding.edtZipCode.getText().toString().isEmpty()) {
            profileBinding.edtZipCode.setError("Enter ZipCode");
            return isValid;
        } else if (profileBinding.edtZipCode.getText().toString().length() < 5) {
            profileBinding.edtZipCode.setError("ZipCode must be 5 characters");
            return isValid;
        }

        if (getActivity() != null) {
            registrationRequest.setAddress(profileBinding.edtAddress.getText().toString());
            registrationRequest.setCity(profileBinding.edtCity.getText().toString());
            registrationRequest.setCountry(profileBinding.edtCountry.getText().toString());
            registrationRequest.setState(profileBinding.edtState.getText().toString());
            registrationRequest.setZipCode(Integer.parseInt(profileBinding.edtZipCode.getText().toString()));
        }

        RadioButton selectedTypeRadioButton;
        boolean isPetFriendly = false;
        if (profileBinding.petType.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getContext(), "Select Pet Type", Toast.LENGTH_LONG).show();
            return isValid;
        } else {
            int selectedId = profileBinding.petType.getCheckedRadioButtonId();
            selectedTypeRadioButton = profileBinding.getRoot().findViewById(selectedId);
        }
        if (profileBinding.edtPetName.getText().toString().isEmpty()) {
            profileBinding.edtPetName.setError("Enter Pet Name");
            return isValid;
        }
        if (profileBinding.edtPetBreed.getText().toString().isEmpty()) {
            profileBinding.edtPetBreed.setError("Enter Pet Breed");
            return isValid;
        }
        if (profileBinding.edtPetAge.getText().toString().isEmpty()) {
            profileBinding.edtPetAge.setError("Enter Pet Age");
            return isValid;
        }
        if (profileBinding.petFriendly.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getContext(), "Select Pet friendly", Toast.LENGTH_LONG).show();
            return isValid;
        } else {
            int selectedId = profileBinding.petType.getCheckedRadioButtonId();
            isPetFriendly = selectedId == 1;
        }


        if (getActivity() != null) {
            RegistrationRequest.PetDetails petDetails = new RegistrationRequest.PetDetails();
            petDetails.setId(profileMvpPresenter.getPetId());
            petDetails.setType(selectedTypeRadioButton.getText().toString());
            petDetails.setName(profileBinding.edtPetName.getText().toString());
            petDetails.setBreed(profileBinding.edtPetBreed.getText().toString());
            petDetails.setAgeInYears(Integer.parseInt(profileBinding.edtPetAge.getText().toString()));
            petDetails.setIsFriendly(isPetFriendly);
            petDetails.setAbout(profileBinding.edtPetAbout.getText().toString());
            petDetails.setUserId(profileMvpPresenter.getUserId());
            registrationRequest.setPetDetails(petDetails);
        }
        return isValid = true;
    }
}