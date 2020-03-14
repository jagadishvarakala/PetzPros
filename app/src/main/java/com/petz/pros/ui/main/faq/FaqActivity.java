package com.petz.pros.ui.main.faq;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.petz.pros.R;
import com.petz.pros.databinding.ActivityFaqBinding;
import com.petz.pros.databinding.ActivityMoreBinding;
import com.petz.pros.ui.base.BaseActivity;
import com.petz.pros.ui.main.about.AboutMvpPresenter;
import com.petz.pros.ui.main.about.AboutMvpView;

import javax.inject.Inject;

public class FaqActivity extends BaseActivity implements FaqMvpView {
    @Inject
    FaqMvpPresenter<FaqMvpView> moreMvpPresenter;
    private ActivityFaqBinding activityFaqBinding;

    public static Intent getStartIntent(Context context){
        return new Intent(context, FaqActivity.class);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityFaqBinding = DataBindingUtil.setContentView(this, R.layout.activity_faq);
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
