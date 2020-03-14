package com.petz.pros.ui.main.ui.more;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.petz.pros.R;
import com.petz.pros.databinding.ActivityMoreBinding;
import com.petz.pros.ui.base.BaseActivity;
import com.petz.pros.ui.main.about.AboutActivity;
import com.petz.pros.ui.main.contact.ContactActivity;
import com.petz.pros.ui.main.faq.FaqActivity;
import com.petz.pros.ui.main.privacy.PrivacyActivity;
import com.petz.pros.ui.main.termsconditions.TermsActivity;

import javax.inject.Inject;

public class MoreActivity extends BaseActivity implements MoreMvpView {
    @Inject
    MoreMvpPresenter<MoreMvpView> moreMvpPresenter;
    private ActivityMoreBinding activityMoreBinding;

    public static Intent getStartIntent(Context context){
        return new Intent(context, MoreActivity.class);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMoreBinding = DataBindingUtil.setContentView(this, R.layout.activity_more);
        getActivityComponent().inject(this);
        moreMvpPresenter.onAttach(this);
        setUp();
    }

    @Override
    protected void setUp() {
        activityMoreBinding.setCallback(moreMvpPresenter);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClickAbout() {
        startActivity(AboutActivity.getStartIntent(this));
    }

    @Override
    public void onClickFAQ() {
        startActivity(FaqActivity.getStartIntent(this));
    }

    @Override
    public void onClickContact() {
        startActivity(ContactActivity.getStartIntent(this));
    }

    @Override
    public void onClickTermsConditions() {
        startActivity(TermsActivity.getStartIntent(this));
    }

    @Override
    public void onClickPrivacy() {
        startActivity(PrivacyActivity.getStartIntent(this));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}
