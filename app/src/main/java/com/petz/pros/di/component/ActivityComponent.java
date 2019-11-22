package com.petz.pros.di.component;


import com.petz.pros.di.PerActivity;
import com.petz.pros.di.module.ActivityModule;
import com.petz.pros.ui.forgotpassword.ForgotPasswordActivity;
import com.petz.pros.ui.login.LoginActivity;
import com.petz.pros.ui.main.MainActivity;
import com.petz.pros.ui.main.NavigationActivity;
import com.petz.pros.ui.main.ui.home.HomeFragment;
import com.petz.pros.ui.registration.OwnerRegistrationActivity;
import com.petz.pros.ui.registration.caretacker.CareTackerRegistrationActivity;
import com.petz.pros.ui.usertype.UserTypeActivity;

import dagger.Component;

/**
 * Created on : Jan 19, 2019
 * Author     : AndroidWave
 * Email    : info@androidwave.com
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {


    void inject(LoginActivity loginActivity);

    void inject(MainActivity mainActivity);

    void inject(UserTypeActivity userTypeActivity);

    void inject(OwnerRegistrationActivity ownerRegistrationActivity);

    void inject(CareTackerRegistrationActivity careTackerRegistrationActivity);

    void inject(ForgotPasswordActivity forgotPasswordActivity);

    void inject(NavigationActivity navigationActivity);

    void inject(HomeFragment homeFragment);
}