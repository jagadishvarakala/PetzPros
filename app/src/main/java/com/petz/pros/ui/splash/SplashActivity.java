package com.petz.pros.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.petz.pros.ui.base.BaseActivity;
import com.petz.pros.ui.main.NavigationActivity;
import com.petz.pros.ui.main.ui.dashboard.DashBoardActivity;
import com.petz.pros.ui.usertype.UserTypeActivity;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity  implements SplashMvpView{
    private static int SPLASH_SCREEN_TIME_OUT=2000;
    @Inject
    SplashMvpPresenter<SplashMvpView> splashMvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        splashMvpPresenter.onAttach(this);

        setUp();
    }

    @Override
    protected void setUp() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!splashMvpPresenter.isUserLogin()) {
                    startActivity(new Intent(SplashActivity.this, UserTypeActivity.class));
                    finish();
                }else{
                    if(splashMvpPresenter.getUserType().equalsIgnoreCase("pet owner")) {
                        startActivity(NavigationActivity.getStartIntent(SplashActivity.this));
                        finish();
                    }else {
                        startActivity(DashBoardActivity.getIntent(SplashActivity.this));
                        finish();
                    }
                }
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
}
