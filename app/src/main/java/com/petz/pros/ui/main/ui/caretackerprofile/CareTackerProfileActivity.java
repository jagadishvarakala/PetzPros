package com.petz.pros.ui.main.ui.caretackerprofile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.google.firebase.iid.FirebaseInstanceId;
import com.petz.pros.R;
import com.petz.pros.data.network.pojo.RegistrationRequest;
import com.petz.pros.databinding.FragmentCareTackerProfileBinding;
import com.petz.pros.ui.base.BaseActivity;
import com.petz.pros.utils.CommonUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import okhttp3.ResponseBody;


public class CareTackerProfileActivity extends BaseActivity implements CareTackerProfileMvpView {


    @Inject
    CareTackerProfileMvpPresenter<CareTackerProfileMvpView> profileMvpPresenter;
    private FragmentCareTackerProfileBinding profileBinding;

    private RegistrationRequest registrationRequest;

    public static Intent getIntent(Context context , boolean isChecked){
        Intent intent = new Intent(context,CareTackerProfileActivity.class);
        intent.putExtra("isAvailabulity", isChecked);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileBinding = DataBindingUtil.setContentView(this, R.layout.fragment_care_tacker_profile);
        getActivityComponent().inject(this);
        profileMvpPresenter.onAttach(CareTackerProfileActivity.this);
        registrationRequest = new RegistrationRequest();
        registrationRequest.setUserType(profileMvpPresenter.getUserType());
        registrationRequest.setId(profileMvpPresenter.getUserId());
        registrationRequest.setFCMDeviceId(FirebaseInstanceId.getInstance().getToken());
        setUp();
    }



    @Override
    public void onSuccessProfileUpdate(RegistrationRequest body) {
        showMessage("Profile updated successfully");
        finish();
    }

    @Override
    public boolean getAvailabulity() {
      return   getIntent().getBooleanExtra("isAvailabulity",false);
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
        if (profileBinding.edtPassword.getText().toString().isEmpty()) {
            profileBinding.edtPassword.setError("Enter Password");
            return isValid;
        } else if (!CommonUtils.validatePassword(profileBinding.edtPassword.getText().toString())) {
            profileBinding.edtPassword.setError("Password must contain at least four characters, including uppercase, lowercase letters and numbers");
            return isValid;
        }
        if (profileBinding.edtConfirmPassword.getText().toString().isEmpty()) {
            profileBinding.edtConfirmPassword.setError("Enter Confirm Password");
            return isValid;
        } else if (!profileBinding.edtPassword.getText().toString().equals(profileBinding.edtConfirmPassword.getText().toString())) {
            profileBinding.edtConfirmPassword.setError("password and confirm password doesn't match");
            return isValid;
        }

        registrationRequest.setIsWalkerAvailable(getAvailabulity());
        registrationRequest.setFirstName(profileBinding.edtName.getText().toString());
        registrationRequest.setLastName(profileBinding.edtLastName.getText().toString());
        registrationRequest.setEmailId(profileBinding.edtEmail.getText().toString());
        registrationRequest.setPhone(profileBinding.edtMobile.getText().toString());
        registrationRequest.setPassword(profileBinding.edtPassword.getText().toString());

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

        registrationRequest.setAddress(profileBinding.edtAddress.getText().toString());
        registrationRequest.setCity(profileBinding.edtCity.getText().toString());
        registrationRequest.setCountry(profileBinding.edtCountry.getText().toString());
        registrationRequest.setState(profileBinding.edtState.getText().toString());
        registrationRequest.setZipCode(Integer.parseInt(profileBinding.edtZipCode.getText().toString()));

        ArrayList<Integer> integers = new ArrayList<>();
        if(profileBinding.dogWalkingCheckBox.isChecked()){
            integers.add(1);
            isValid = true;
        }
        if(profileBinding.dogBoardingCheckBox.isChecked()){
            integers.add(2);
            isValid = true;
        }
        if(profileBinding.dogSittingCheckBox.isChecked()){
            integers.add(3);
            isValid = true;
        }
        if(profileBinding.dogDayCareCheckBox.isChecked()){
            integers.add(4);
            isValid = true;
        }
        if(profileBinding.catBoardingCheckBox.isChecked()){
            integers.add(5);
            isValid = true;
        }
        if(profileBinding.catSittingCheckBox.isChecked()){
            integers.add(6);
            isValid = true;
        }
        if(profileBinding.dropVisitsCheckBox.isChecked()){
            integers.add(7);
            isValid = true;
        }
        if(TextUtils.isEmpty(profileBinding.edtChargePerHour.getText().toString())){
            profileBinding.edtChargePerHour.setError("Please enter Charge");
            isValid = false;
        }
        if(!isValid){
            Toast.makeText(this,"Select service type",Toast.LENGTH_LONG).show();
            return isValid;
        }

        registrationRequest.setServiceIdList(integers);
        registrationRequest.setChargePerHour(Integer.parseInt(profileBinding.edtChargePerHour.getText().toString()));

        if (profileBinding.edtAccountNumber.getText().toString().isEmpty()) {
            profileBinding.edtAccountNumber.setError("Enter Account Number");
            return isValid;
        }
        if (profileBinding.edtBankName.getText().toString().isEmpty()) {
            profileBinding.edtBankName.setError("Enter Bank Name");
            return isValid;
        }
        if (profileBinding.edtBranch.getText().toString().isEmpty()) {
            profileBinding.edtBranch.setError("Enter Branch");
            return isValid;
        }
        if (profileBinding.edtIFSCCode.getText().toString().isEmpty()) {
            profileBinding.edtIFSCCode.setError("Enter IFSC code");
            return isValid;
        }

        registrationRequest.setAccountNumber(profileBinding.edtAccountNumber.getText().toString());
        registrationRequest.setBankName(profileBinding.edtBankName.getText().toString());
        registrationRequest.setBranch(profileBinding.edtBranch.getText().toString());
        registrationRequest.setIfscCode(profileBinding.edtIFSCCode.getText().toString());

        return true;
    }

    @Override
    protected void setUp() {
        if(getSupportActionBar() != null)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        profileBinding.setProfile(profileMvpPresenter.getProfileDetails());

        profileBinding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toValidatePetDetails()) {
                    profileMvpPresenter.petOwnerProfileUpdate(registrationRequest);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}