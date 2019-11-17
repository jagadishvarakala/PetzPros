package com.petz.cleancode.di.component;


import com.petz.cleancode.di.PerActivity;
import com.petz.cleancode.di.module.ActivityModule;
import com.petz.cleancode.ui.login.LoginActivity;
import com.petz.cleancode.ui.main.MainActivity;
import com.petz.cleancode.ui.registration.OwnerRegistrationActivity;
import com.petz.cleancode.ui.usertype.UserTypeActivity;

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

}