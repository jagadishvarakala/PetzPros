package com.petz.pros.ui.main.privacy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.petz.pros.R;
import com.petz.pros.databinding.ActivityMoreBinding;
import com.petz.pros.databinding.ActivityPrivacyBinding;
import com.petz.pros.ui.base.BaseActivity;
import com.petz.pros.ui.main.about.AboutMvpPresenter;
import com.petz.pros.ui.main.about.AboutMvpView;

import javax.inject.Inject;

public class PrivacyActivity extends BaseActivity implements PrivacyMvpView {
    @Inject
    PrivacyMvpPresenter<PrivacyMvpView> moreMvpPresenter;
    private ActivityPrivacyBinding activityPrivacyBinding;

    public static Intent getStartIntent(Context context){
        return new Intent(context, PrivacyActivity.class);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPrivacyBinding = DataBindingUtil.setContentView(this, R.layout.activity_privacy);
        getActivityComponent().inject(this);
        moreMvpPresenter.onAttach(this);
        setUp();
    }

    @Override
    protected void setUp() {
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}
