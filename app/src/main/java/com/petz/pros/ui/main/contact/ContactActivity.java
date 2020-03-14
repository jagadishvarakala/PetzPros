package com.petz.pros.ui.main.contact;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.petz.pros.R;
import com.petz.pros.databinding.ActivityContactBinding;
import com.petz.pros.databinding.ActivityMoreBinding;
import com.petz.pros.ui.base.BaseActivity;
import com.petz.pros.ui.main.about.AboutMvpPresenter;
import com.petz.pros.ui.main.about.AboutMvpView;

import javax.inject.Inject;

public class ContactActivity extends BaseActivity implements ContactMvpView {
    @Inject
    ContactMvpPresenter<ContactMvpView> moreMvpPresenter;
    private ActivityContactBinding activityContactBinding;

    public static Intent getStartIntent(Context context){
        return new Intent(context, ContactActivity.class);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityContactBinding = DataBindingUtil.setContentView(this, R.layout.activity_contact);
        getActivityComponent().inject(this);
        moreMvpPresenter.onAttach(this);
        setUp();
    }

    @Override
    protected void setUp() {
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        activityContactBinding.setCallback(moreMvpPresenter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

    @Override
    public String getName() {
        return activityContactBinding.editName.getText().toString();
    }

    @Override
    public String getEmail() {
        return activityContactBinding.edtEmail.getText().toString();
    }

    @Override
    public String getPhone() {
        return activityContactBinding.edtPhone.getText().toString();
    }

    @Override
    public String getSubject() {
        return activityContactBinding.edtSubject.getText().toString();
    }

    @Override
    public String getMessage() {
        return activityContactBinding.edtMessage.getText().toString();
    }

    @Override
    public void showErrorName(String message) {
        activityContactBinding.editName.setError(message);
    }

    @Override
    public void showErrorEmail(String message) {
        activityContactBinding.edtEmail.setError(message);
    }

    @Override
    public void showErrorPhone(String message) {
        activityContactBinding.edtPhone.setError(message);
    }

    @Override
    public void showErrorSubject(String message) {
        activityContactBinding.edtSubject.setError(message);
    }
}
