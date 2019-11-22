package com.petz.pros.ui.forgotpassword;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.petz.pros.R;
import com.petz.pros.databinding.ActivityForgotPasswordBinding;
import com.petz.pros.ui.base.BaseActivity;

import java.util.Objects;

import javax.inject.Inject;

import okhttp3.ResponseBody;

public class ForgotPasswordActivity extends BaseActivity implements ForgotPasswordMvpView {

    @Inject
    ForgotPasswordPresenter<ForgotPasswordMvpView> passwordPresenter;

    protected ActivityForgotPasswordBinding activityForgotPasswordBinding;

    public static Intent getStartIntent(Context context, int userType) {
        Intent intent = new Intent(context, ForgotPasswordActivity.class);
        intent.putExtra("userType", userType);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityForgotPasswordBinding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password);
        getActivityComponent().inject(this);
        passwordPresenter.onAttach(this);
        setUp();
    }

    @Override
    protected void setUp() {
        activityForgotPasswordBinding.btnSubmit.setOnClickListener(view -> passwordPresenter.validateForm());
    }

    @Override
    public String getInputEmail() {
        return Objects.requireNonNull(activityForgotPasswordBinding.inputEmail.getText()).toString();
    }

    @Override
    public String getUserType() {
        return getIntent().getIntExtra("userType", 0) == 1 ? "pet owner" : "pet care taker";
    }

    @Override
    public void showInputEmailError(String message) {
        activityForgotPasswordBinding.inputEmail.setError(message);
    }

    @Override
    public void onForgotPasswordSuccess(ResponseBody message) {
        finish();
    }
}
