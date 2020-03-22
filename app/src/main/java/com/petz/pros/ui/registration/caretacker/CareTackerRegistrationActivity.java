package com.petz.pros.ui.registration.caretacker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.firebase.iid.FirebaseInstanceId;
import com.petz.pros.R;
import com.petz.pros.data.network.pojo.RegistrationRequest;
import com.petz.pros.databinding.ActivityOwnerRegistrationBinding;
import com.petz.pros.ui.base.BaseActivity;
import com.petz.pros.ui.main.NavigationActivity;
import com.petz.pros.ui.main.ui.dashboard.DashBoardActivity;
import com.petz.pros.ui.registration.OwnerRegistrationActivity;
import com.petz.pros.ui.registration.OwnerRegistrationMvpView;
import com.petz.pros.ui.registration.OwnerRegistrationPresenter;
import com.petz.pros.ui.registration.addressdetails.AddressDetailsFragment;
import com.petz.pros.ui.registration.bankdetails.BankDetailsFragment;
import com.petz.pros.ui.registration.personaldetails.PersonalDetailsFragment;
import com.petz.pros.ui.registration.servicedetails.ServiceDetailsFragment;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;

import javax.inject.Inject;

import okhttp3.ResponseBody;

public class CareTackerRegistrationActivity extends BaseActivity implements CareTackerRegistrationMvpView{

    @Inject
    CareTackerRegistrationPresenter<CareTackerRegistrationMvpView> registrationMvpPresenter;

    private ActivityOwnerRegistrationBinding ownerRegistrationBinding;
    public RegistrationRequest registrationRequest;

    public static Intent getStartIntent(Context context, int userType) {
        Intent intent = new Intent(context, CareTackerRegistrationActivity.class);
        intent.putExtra("userType",userType);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ownerRegistrationBinding = DataBindingUtil.setContentView(this, R.layout.activity_owner_registration);
        getActivityComponent().inject(this);
        registrationMvpPresenter.onAttach(CareTackerRegistrationActivity.this);
        registrationRequest = new RegistrationRequest();
        registrationRequest.setUserType(getIntent().getIntExtra("userType",0) == 1 ? "pet owner":"pet care taker");
        registrationRequest.setFCMDeviceId(FirebaseInstanceId.getInstance().getToken());
        registrationRequest.setIsWalkerAvailable(true);
        setUp();
    }

    @Override
    protected void setUp() {
        if(getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Care Taker Registration");
        }
        ownerRegistrationBinding.stepView.getState()
                .selectedTextColor(ContextCompat.getColor(this, R.color.colorAccent))
                .animationType(StepView.ANIMATION_ALL)
                .selectedCircleColor(ContextCompat.getColor(this, R.color.colorAccent))
                .selectedCircleRadius(getResources().getDimensionPixelSize(R.dimen.dp14))
                .selectedStepNumberColor(ContextCompat.getColor(this, R.color.colorWhite))
                // You should specify only stepsNumber or steps array of strings.
                // In case you specify both steps array is chosen.
                .steps(new ArrayList<String>() {{
                    add("Personal\nDetails");
                    add("Address\nDetails");
                    add("Service\nDetails");
                    add("Bank\nDetails");
                }})
                // You should specify only steps number or steps array of strings.
                // In case you specify both steps array is chosen.
                .stepsNumber(4)
                .animationDuration(getResources().getInteger(android.R.integer.config_shortAnimTime))
                .stepLineWidth(getResources().getDimensionPixelSize(R.dimen.dp1))
                .textSize(getResources().getDimensionPixelSize(R.dimen.sp14))
                .stepNumberTextSize(getResources().getDimensionPixelSize(R.dimen.sp14))
              //  .typeface(ResourcesCompat.getFont(context, R.font.roboto_italic))
                // other state methods are equal to the corresponding xml attributes
                .commit();

        ownerRegistrationBinding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyPagerAdapter myPagerAdapter = (MyPagerAdapter) ownerRegistrationBinding.viewpager.getAdapter();
                Fragment fragment = myPagerAdapter.getCurrentFragment();
                if(fragment instanceof PersonalDetailsFragment){
                   if( ((PersonalDetailsFragment)fragment).toValidatePersonalDetails()){
                       ownerRegistrationBinding.stepView.go(1,true);
                       ownerRegistrationBinding.viewpager.setCurrentItem(1);
                   }
                }else if(fragment instanceof AddressDetailsFragment){
                    if( ((AddressDetailsFragment)fragment).toValidateAddressDetails()){
                        ownerRegistrationBinding.stepView.go(2,true);
                        ownerRegistrationBinding.viewpager.setCurrentItem(2);
                    }
                }else if(fragment instanceof ServiceDetailsFragment){
                    if(((ServiceDetailsFragment)fragment).toValidateServiceDetails()){
                        ownerRegistrationBinding.stepView.go(3,true);
                        ownerRegistrationBinding.viewpager.setCurrentItem(3);
                    }
                }else if(fragment instanceof BankDetailsFragment){
                    if(((BankDetailsFragment)fragment).toValidateBankDetailsDetails()){
                        ownerRegistrationBinding.stepView.done(true);
                        registrationMvpPresenter.petCareTackerRegistration(registrationRequest);
                    }
                }

            }
        });
        ownerRegistrationBinding.viewpager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
    }

    @Override
    public void onSuccessRegistration(ResponseBody body) {
        showMessage("Successfully Registration Completed");
        startActivity(DashBoardActivity.getIntent(CareTackerRegistrationActivity.this));
        finish();
    }


    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        private Fragment mCurrentFragment;

        public Fragment getCurrentFragment() {
            return mCurrentFragment;
        }
        @Override
        public Fragment getItem(int pos) {
            switch (pos) {

                case 0:
                    return PersonalDetailsFragment.newInstance();
                case 1:
                    return AddressDetailsFragment.newInstance();
                case 2:
                    return ServiceDetailsFragment.newInstance();
                case 3:
                    return BankDetailsFragment.newInstance();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            if (getCurrentFragment() != object) {
                mCurrentFragment = ((Fragment) object);
            }
            super.setPrimaryItem(container, position, object);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        MyPagerAdapter myPagerAdapter = (MyPagerAdapter) ownerRegistrationBinding.viewpager.getAdapter();
        Fragment fragment = myPagerAdapter.getCurrentFragment();
        if(fragment instanceof BankDetailsFragment){
            ownerRegistrationBinding.stepView.go(2,true);
            ownerRegistrationBinding.viewpager.setCurrentItem(2);
        }else if(fragment instanceof ServiceDetailsFragment){
            ownerRegistrationBinding.stepView.go(1,true);
            ownerRegistrationBinding.viewpager.setCurrentItem(1);
        }else if(fragment instanceof AddressDetailsFragment){
            ownerRegistrationBinding.stepView.go(0,true);
            ownerRegistrationBinding.viewpager.setCurrentItem(0);
        }else if(fragment instanceof PersonalDetailsFragment){
            finish();
        }
    }
}
