package com.petz.pros.ui.main.about;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.petz.pros.R;
import com.petz.pros.databinding.ActivityAboutBinding;
import com.petz.pros.databinding.ActivityMoreBinding;
import com.petz.pros.ui.base.BaseActivity;
import com.petz.pros.ui.main.ui.more.MoreMvpPresenter;
import com.petz.pros.ui.main.ui.more.MoreMvpView;

import javax.inject.Inject;

public class AboutActivity extends BaseActivity implements AboutMvpView {
    @Inject
    AboutMvpPresenter<AboutMvpView> moreMvpPresenter;
    private ActivityAboutBinding activityAboutBinding;

    public static Intent getStartIntent(Context context){
        return new Intent(context, AboutActivity.class);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAboutBinding = DataBindingUtil.setContentView(this, R.layout.activity_about);
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
