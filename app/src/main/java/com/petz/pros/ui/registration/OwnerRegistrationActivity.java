package com.petz.cleancode.ui.registration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.petz.cleancode.R;
import com.petz.cleancode.databinding.ActivityOwnerRegistrationBinding;
import com.petz.cleancode.ui.base.BaseActivity;
import com.petz.cleancode.ui.registration.addressdetails.AddressDetailsFragment;
import com.petz.cleancode.ui.registration.personaldetails.PersonalDetailsFragment;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;

import javax.inject.Inject;

public class OwnerRegistrationActivity extends BaseActivity implements OwnerRegistrationMvpView{

    @Inject
    OwnerRegistrationPresenter<OwnerRegistrationMvpView> registrationMvpPresenter;

    private ActivityOwnerRegistrationBinding ownerRegistrationBinding;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, OwnerRegistrationActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ownerRegistrationBinding = DataBindingUtil.setContentView(this, R.layout.activity_owner_registration);
        getActivityComponent().inject(this);
        registrationMvpPresenter.onAttach(OwnerRegistrationActivity.this);
        setUp();
    }

    @Override
    protected void setUp() {
        ownerRegistrationBinding.stepView.getState()
                .selectedTextColor(ContextCompat.getColor(this, R.color.colorAccent))
                .animationType(StepView.ANIMATION_CIRCLE)
                .selectedCircleColor(ContextCompat.getColor(this, R.color.colorAccent))
                .selectedCircleRadius(getResources().getDimensionPixelSize(R.dimen.dp14))
                .selectedStepNumberColor(ContextCompat.getColor(this, R.color.colorWhite))
                // You should specify only stepsNumber or steps array of strings.
                // In case you specify both steps array is chosen.
                .steps(new ArrayList<String>() {{
                    add("Personal Details");
                    add("Address Details");
                }})
                // You should specify only steps number or steps array of strings.
                // In case you specify both steps array is chosen.
                .stepsNumber(2)
                .animationDuration(getResources().getInteger(android.R.integer.config_shortAnimTime))
                .stepLineWidth(getResources().getDimensionPixelSize(R.dimen.dp1))
                .textSize(getResources().getDimensionPixelSize(R.dimen.sp14))
                .stepNumberTextSize(getResources().getDimensionPixelSize(R.dimen.sp16))
              //  .typeface(ResourcesCompat.getFont(context, R.font.roboto_italic))
                // other state methods are equal to the corresponding xml attributes
                .commit();

        ownerRegistrationBinding.viewpager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
    }


    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos) {

                case 0: return PersonalDetailsFragment.newInstance("FirstFragment, Instance 1");
                case 1: return AddressDetailsFragment.newInstance("SecondFragment, Instance 1");
                default: return PersonalDetailsFragment.newInstance("ThirdFragment, Default");
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
