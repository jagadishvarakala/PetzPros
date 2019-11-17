package com.petz.cleancode.ui.usertype;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.petz.cleancode.R;
import com.petz.cleancode.databinding.ActivityUserTypeBinding;
import com.petz.cleancode.ui.base.BaseActivity;
import com.petz.cleancode.ui.login.LoginActivity;

import javax.inject.Inject;

public class UserTypeActivity extends BaseActivity implements UserTypeMvpView{

    @Inject
    UserTypePresenter<UserTypeMvpView> userTypePresenter;

    private ActivityUserTypeBinding userTypeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userTypeBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_type);
        getActivityComponent().inject(this);
        userTypePresenter.onAttach(UserTypeActivity.this);
        setUp();
    }

    @Override
    protected void setUp() {
        userTypeBinding.petOwnerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserTypeActivity.this,LoginActivity.class);
                intent.putExtra("userType",1);
                startActivity(intent);
            }
        });

        userTypeBinding.petCareTakerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserTypeActivity.this,LoginActivity.class);
                intent.putExtra("userType",2);
                startActivity(intent);
            }
        });
    }
}
