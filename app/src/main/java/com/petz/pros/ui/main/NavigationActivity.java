package com.petz.pros.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.petz.pros.R;
import com.petz.pros.data.network.pojo.FeedItem;
import com.petz.pros.ui.base.BaseActivity;
import com.petz.pros.ui.splash.SplashActivity;
import com.petz.pros.ui.usertype.UserTypeActivity;

import java.util.List;

import javax.inject.Inject;

public class NavigationActivity extends BaseActivity implements MainMvpView {

    @Inject
    MainMvpPresenter<MainMvpView> mPresenter;

    private AppBarConfiguration mAppBarConfiguration;
    private NavigationView navigationView;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, NavigationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        getActivityComponent().inject(this);
        mPresenter.onAttach(NavigationActivity.this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_service, R.id.nav_profile,  R.id.nav_bookings,
                R.id.nav_history, R.id.nav_about, R.id.nav_faq, R.id.nav_contact, R.id.nav_terms, R.id.nav_privacy)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        setUp();
    }

    @Override
    protected void setUp() {
        Button btnLogout = (Button) findViewById(R.id.user_logout_btn);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.userLogout();
            }
        });

        boolean extras = getIntent().getBooleanExtra("KEY", false);;
        if (extras) {

            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            navController.navigate(R.id.nav_bookings);

        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        View header = navigationView.getHeaderView(0);
        TextView textUserName = (TextView) header.findViewById(R.id.user_name);
        TextView textUserEmail = (TextView) header.findViewById(R.id.user_email);
        textUserName.setText(mPresenter.getUserName());
        textUserEmail.setText(mPresenter.getUserEmail());
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void updateFeed(List<FeedItem> feedItemList) {

    }

    @Override
    public void navigateUserSelection() {
        startActivity(new Intent(NavigationActivity.this,UserTypeActivity.class));
        finish();
    }
}
