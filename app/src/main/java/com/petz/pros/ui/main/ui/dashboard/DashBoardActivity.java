package com.petz.pros.ui.main.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.petz.pros.R;
import com.petz.pros.data.network.pojo.RegistrationRequest;
import com.petz.pros.databinding.FragmentDashBoardBinding;
import com.petz.pros.ui.base.BaseActivity;
import com.petz.pros.ui.login.LoginActivity;
import com.petz.pros.ui.main.ui.caretackerprofile.CareTackerProfileActivity;
import com.petz.pros.ui.main.ui.more.MoreActivity;
import com.petz.pros.ui.main.ui.past.PastServicesActivity;
import com.petz.pros.ui.main.ui.pending.PendingServicesActivity;
import com.petz.pros.ui.main.ui.rejected.RejectedServicesActivity;
import com.petz.pros.ui.main.upcoming.UpcomingServicesActivity;
import com.petz.pros.ui.usertype.UserTypeActivity;
import com.petz.pros.utils.Singleton;

import javax.inject.Inject;

public class DashBoardActivity extends BaseActivity implements DashBoardMvpView {

    @Inject
    DashBoardMvpPresenter<DashBoardMvpView> boardMvpPresenter;

    private FragmentDashBoardBinding fragmentDashBoardBinding;

    public static Intent getIntent(Context context) {
        return new Intent(context, DashBoardActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentDashBoardBinding = DataBindingUtil.setContentView(this, R.layout.fragment_dash_board);
        getActivityComponent().inject(this);
        boardMvpPresenter.onAttach(this);
        setUp();
    }


    @Override
    protected void setUp() {
        fragmentDashBoardBinding.setName(boardMvpPresenter.getUserName());
        fragmentDashBoardBinding.setAvailability(true);
        fragmentDashBoardBinding.setCallback(boardMvpPresenter);


        fragmentDashBoardBinding.availability.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                boardMvpPresenter.petOwnerProfileUpdate(boardMvpPresenter.getProfileDetails(isChecked));
            }
        });


        boardMvpPresenter.careTackerLogin();

    }

    @Override
    public void onClickPendingService() {
        startActivity(PendingServicesActivity.getIntent(this));
    }

    @Override
    public void onCLickRejectedService() {
        startActivity(RejectedServicesActivity.getIntent(this));
    }

    @Override
    public void onClickPetService() {
        startActivity(PastServicesActivity.getIntent(this));
    }

    @Override
    public void onClickUpComingService() {
        startActivity(UpcomingServicesActivity.getIntent(this));
    }

    @Override
    public void onClickProfile() {
        startActivity(CareTackerProfileActivity.getIntent(this));
    }

    @Override
    public void onCLickMore() {
        startActivity(MoreActivity.getStartIntent(this));
    }

    @Override
    public void onClickLogout() {
        startActivity(new Intent(DashBoardActivity.this, UserTypeActivity.class));
        finish();
    }

    @Override
    public void onSuccessProfileUpdate(RegistrationRequest body) {
        //showMessage("Profile updated successfully");
    }

    @Override
    public void onAvalibulity(boolean isAvailabl) {
        fragmentDashBoardBinding.availability.setChecked(isAvailabl);
    }

    @Override
    public void invalidCrediential() {
        Singleton.getInstance().selectedUserType = "pet care taker";
        Intent intent = new Intent(DashBoardActivity.this, LoginActivity.class);
        intent.putExtra("userType",2);
        startActivity(intent);
        finish();
    }
}
