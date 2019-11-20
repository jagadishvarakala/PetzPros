package com.petz.pros.ui.login;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.petz.pros.R;
import com.petz.pros.databinding.ActivityLoginBinding;
import com.petz.pros.ui.base.BaseActivity;
import com.petz.pros.ui.main.MainActivity;
import com.petz.pros.ui.registration.OwnerRegistrationActivity;

import javax.inject.Inject;

import okhttp3.ResponseBody;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements LoginMvpView {
    @Inject
    LoginMvpPresenter<LoginMvpView> mPresenter;


    private ActivityLoginBinding activityLoginBinding;
    private int userType = 0;

    // UI references.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        getActivityComponent().inject(this);
        mPresenter.onAttach(LoginActivity.this);
        setUp();
    }

    @Override
    protected void setUp() {
        userType = getIntent().getIntExtra("userType",0);
        activityLoginBinding.btnLogin.setOnClickListener(v -> {
          mPresenter.onLoginClick();
        });

        activityLoginBinding.linkSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userType == 1){
                    startActivity(OwnerRegistrationActivity.getStartIntent(LoginActivity.this));
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }

    @Override
    public void onLoginSuccess(ResponseBody  responseBody) {
        startActivity(MainActivity.getStartIntent(this));
    }

    @Override
    public String getEmail() {
        return activityLoginBinding.inputEmail.getText().toString();
    }

    @Override
    public String getPassword() {
        return activityLoginBinding.inputPassword.getText().toString();
    }

    @Override
    public String getUserType() {
        return userType == 1 ? "pet owner":"pet caretaker";
    }

    @Override
    public void showInputEmailError(String errorMessage) {
        activityLoginBinding.inputEmail.setError(errorMessage);
       // showMessage(getString(R.string.input_invalid));
    }

    @Override
    public void showInputPasswordError(String errorMessage){
        activityLoginBinding.inputPassword.setError(errorMessage);
    }

    @Override
    public void setPassword(String password) {
        activityLoginBinding.inputPassword.setText(password);
    }

    @Override
    public void setEmail(String email) {
        activityLoginBinding.inputPassword.setText(email);
    }


}

