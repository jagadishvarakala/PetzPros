package com.petz.pros.ui.registration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.firebase.iid.FirebaseInstanceId;
import com.petz.pros.R;
import com.petz.pros.data.BaseDataManager;
import com.petz.pros.data.network.pojo.RegistrationRequest;
import com.petz.pros.databinding.ActivityOwnerRegistrationBinding;
import com.petz.pros.ui.base.BaseActivity;
import com.petz.pros.ui.main.NavigationActivity;
import com.petz.pros.ui.registration.addressdetails.AddressDetailsFragment;
import com.petz.pros.ui.registration.personaldetails.PersonalDetailsFragment;
import com.petz.pros.ui.registration.petdetails.PetDetailsFragment;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;

import javax.inject.Inject;

import okhttp3.ResponseBody;

public class OwnerRegistrationActivity extends BaseActivity implements OwnerRegistrationMvpView{

    @Inject
    OwnerRegistrationPresenter<OwnerRegistrationMvpView> registrationMvpPresenter;

    private ActivityOwnerRegistrationBinding ownerRegistrationBinding;
    public RegistrationRequest registrationRequest;

    public static Intent getStartIntent(Context context, int userType) {
        Intent intent = new Intent(context, OwnerRegistrationActivity.class);
        intent.putExtra("userType",userType);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ownerRegistrationBinding = DataBindingUtil.setContentView(this, R.layout.activity_owner_registration);
        getActivityComponent().inject(this);
        registrationMvpPresenter.onAttach(OwnerRegistrationActivity.this);
        registrationRequest = new RegistrationRequest();
        registrationRequest.setUserType(getIntent().getIntExtra("userType",0) == 1 ? "pet owner":"pet care taker");
        registrationRequest.setFCMDeviceId(FirebaseInstanceId.getInstance().getToken());
        setUp();
    }

    @Override
    protected void setUp() {
        if(getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Pet Owner Registration");
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
                    add("Pet\nDetails");
                }})
                // You should specify only steps number or steps array of strings.
                // In case you specify both steps array is chosen.
                .stepsNumber(3)
                .animationDuration(getResources().getInteger(android.R.integer.config_shortAnimTime))
                .stepLineWidth(getResources().getDimensionPixelSize(R.dimen.dp1))
                .textSize(getResources().getDimensionPixelSize(R.dimen.sp14))
                .stepNumberTextSize(getResources().getDimensionPixelSize(R.dimen.sp14))
              //  .typeface(ResourcesCompat.getFont(context, R.font.roboto_italic))
                // other state methods are equal to the corresponding xml attributes
                .commit();

        ownerRegistrationBinding.btnNext.setOnClickListener(view -> {
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
            }else if(fragment instanceof PetDetailsFragment){
                if(((PetDetailsFragment)fragment).toValidatePetDetails()){
                    ownerRegistrationBinding.stepView.done(true);
                    registrationMvpPresenter.petOwnerRegistration(registrationRequest);
                }
            }

        });
        ownerRegistrationBinding.viewpager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
    }

    @Override
    public void onSuccessRegistration(ResponseBody body) {
        showMessage("Successfully Registration Completed");
        startActivity(NavigationActivity.getStartIntent(OwnerRegistrationActivity.this));
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
                    return PetDetailsFragment.newInstance();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
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
        if(fragment instanceof PetDetailsFragment){
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
