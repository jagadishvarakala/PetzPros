package com.petz.pros.ui.registration.personaldetails;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.petz.pros.R;
import com.petz.pros.data.network.ApiClient;
import com.petz.pros.data.network.ApiInterface;
import com.petz.pros.data.network.pojo.RegistrationRequest;
import com.petz.pros.databinding.FragmentPersonalDetailsBinding;
import com.petz.pros.ui.base.BaseFragment;
import com.petz.pros.ui.main.ui.home.HomeFragment;
import com.petz.pros.ui.main.ui.home.HomeMvpPresenter;
import com.petz.pros.ui.main.ui.home.HomeMvpView;
import com.petz.pros.ui.registration.OwnerRegistrationActivity;
import com.petz.pros.ui.registration.caretacker.CareTackerRegistrationActivity;
import com.petz.pros.utils.CommonUtils;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonalDetailsFragment extends BaseFragment implements PersonalMvpView {
    @Inject
    PersonalMvpPresenter<PersonalMvpView> mPresenter;
    private FragmentPersonalDetailsBinding personalDetailsBinding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        personalDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_personal_details, container, false);
        getActivityComponent().inject(this);
        mPresenter.onAttach(PersonalDetailsFragment.this);
        return personalDetailsBinding.getRoot();
    }


    public static PersonalDetailsFragment newInstance() {
        PersonalDetailsFragment f = new PersonalDetailsFragment();
        return f;
    }

    public boolean toValidatePersonalDetails(){
        boolean isValid = false;
        if(personalDetailsBinding.edtName.getText().toString().isEmpty()){
            personalDetailsBinding.edtName.setError("Enter Name");
          return   isValid ;
        }else if(personalDetailsBinding.edtName.getText().toString().length() < 4){
            personalDetailsBinding.edtName.setError("Name must be more then 4 characters");
            return isValid ;
        }
        if(personalDetailsBinding.edtLastName.getText().toString().isEmpty()){
            personalDetailsBinding.edtLastName.setError("Enter Last Name");
            return isValid ;
        }
        if(personalDetailsBinding.edtEmail.getText().toString().isEmpty()){
            personalDetailsBinding.edtEmail.setError("Enter email");
            return isValid ;
        }else if(!CommonUtils.isValidEmail(personalDetailsBinding.edtEmail.getText())){
            personalDetailsBinding.edtEmail.setError("Please enter a valid email");
            return isValid;
        }
        if(personalDetailsBinding.edtMobile.getText().toString().isEmpty()){
            personalDetailsBinding.edtMobile.setError("Enter Mobile Number");
            return isValid ;
        }else if(personalDetailsBinding.edtMobile.getText().toString().length() < 10){
            personalDetailsBinding.edtMobile.setError("Please enter valid mobile number");
            return isValid ;
        }
        if(personalDetailsBinding.edtPassword.getText().toString().isEmpty()){
            personalDetailsBinding.edtPassword.setError("Enter Password");
            return isValid ;
        }else if(!CommonUtils.validatePassword(personalDetailsBinding.edtPassword.getText().toString())){
            personalDetailsBinding.edtPassword.setError("Password must contain at least four characters, including uppercase, lowercase letters and numbers");
            return isValid ;
        }
        if(personalDetailsBinding.edtConfirmPassword.getText().toString().isEmpty()){
            personalDetailsBinding.edtConfirmPassword.setError("Enter Confirm Password");
            return isValid ;
        }else if(!personalDetailsBinding.edtPassword.getText().toString().equals(personalDetailsBinding.edtConfirmPassword.getText().toString())){
            personalDetailsBinding.edtConfirmPassword.setError("password and confirm password doesn't match");
            return isValid;
        }

        if(getActivity()!= null){
            if(getActivity() instanceof OwnerRegistrationActivity){
                ((OwnerRegistrationActivity)getActivity()).registrationRequest.setFirstName(personalDetailsBinding.edtName.getText().toString());
                ((OwnerRegistrationActivity)getActivity()).registrationRequest.setLastName(personalDetailsBinding.edtLastName.getText().toString());
                ((OwnerRegistrationActivity)getActivity()).registrationRequest.setEmailId(personalDetailsBinding.edtEmail.getText().toString());
                ((OwnerRegistrationActivity)getActivity()).registrationRequest.setPhone(personalDetailsBinding.edtMobile.getText().toString());
                ((OwnerRegistrationActivity)getActivity()).registrationRequest.setPassword(personalDetailsBinding.edtPassword.getText().toString());
            }else if(getActivity() instanceof CareTackerRegistrationActivity){
                ((CareTackerRegistrationActivity)getActivity()).registrationRequest.setFirstName(personalDetailsBinding.edtName.getText().toString());
                ((CareTackerRegistrationActivity)getActivity()).registrationRequest.setLastName(personalDetailsBinding.edtLastName.getText().toString());
                ((CareTackerRegistrationActivity)getActivity()).registrationRequest.setEmailId(personalDetailsBinding.edtEmail.getText().toString());
                ((CareTackerRegistrationActivity)getActivity()).registrationRequest.setPhone(personalDetailsBinding.edtMobile.getText().toString());
                ((CareTackerRegistrationActivity)getActivity()).registrationRequest.setPassword(personalDetailsBinding.edtPassword.getText().toString());
            }
        }
        isValid = true;
        return isValid;
    }


    @Override
    protected void setUp(View view) {
//        personalDetailsBinding.edtEmail.setOnEditorActionListener(
//                new EditText.OnEditorActionListener() {
//                    @Override
//                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
//                                actionId == EditorInfo.IME_ACTION_DONE ||
//                                actionId == EditorInfo.IME_ACTION_NEXT ||
//                                event != null &&
//                                        event.getAction() == KeyEvent.ACTION_DOWN &&
//                                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
//                            if (event == null || !event.isShiftPressed()) {
//                                // the user is done typing.
//                                mPresenter.toValidateEmailPhone(v.getText().toString(),true);
//                                return true; // consume.
//                            }
//                        }
//                        return false; // pass on to other listeners.
//                    }
//                }
//        );
        final boolean[] isEditEmail = {false};
        personalDetailsBinding.edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                isEditEmail[0] =true;

            }
        });

        personalDetailsBinding.edtEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b && isEditEmail[0]) {
                    isEditEmail[0] = false;
                    if (CommonUtils.isValidEmail(personalDetailsBinding.edtEmail.getText().toString())) {
                        mPresenter.toValidateEmailPhone(personalDetailsBinding.edtEmail.getText().toString(),true);
                    }
                }
            }
        });

        personalDetailsBinding.edtMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(editable.length()==10){
                    mPresenter.toValidateEmailPhone(editable.toString(),false);
                }

            }
        });

//        personalDetailsBinding.edtMobile.setOnEditorActionListener(
//                new EditText.OnEditorActionListener() {
//                    @Override
//                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
//                                actionId == EditorInfo.IME_ACTION_DONE ||
//                                actionId == EditorInfo.IME_ACTION_NEXT ||
//                                event != null &&
//                                        event.getAction() == KeyEvent.ACTION_DOWN &&
//                                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
//                            if (event == null || !event.isShiftPressed()) {
//                                // the user is done typing.
//                                mPresenter.toValidateEmailPhone(v.getText().toString(),false);
//                                return true; // consume.
//                            }
//                        }
//                        return false; // pass on to other listeners.
//                    }
//                }
//        );
    }

    @Override
    public void onSuccessEmailResponse(boolean b) {
        if(b)
            personalDetailsBinding.edtEmail.setError("Email already exist");
        else
            personalDetailsBinding.edtEmail.setError(null);
    }

    @Override
    public void onSuccessMobileResponse(boolean b) {
        if(b)
            personalDetailsBinding.edtMobile.setError("Mobile already exist");
        else
            personalDetailsBinding.edtMobile.setError(null);
    }
}
