package com.petz.pros.di.module;

import android.content.Context;

import com.petz.pros.di.ActivityContext;
import com.petz.pros.di.PerActivity;
import com.petz.pros.ui.login.LoginMvpPresenter;
import com.petz.pros.ui.login.LoginMvpView;
import com.petz.pros.ui.login.LoginPresenter;
import com.petz.pros.ui.main.MainMvpPresenter;
import com.petz.pros.ui.main.MainMvpView;
import com.petz.pros.ui.main.MainPresenter;
import com.petz.pros.ui.main.RssAdapter;
import com.petz.pros.utils.rx.AppSchedulerProvider;
import com.petz.pros.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created on : Jan 19, 2019
 * Author     : AndroidWave
 * Email    : info@androidwave.com
 */
@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }


    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }


    @Provides
    @PerActivity
    LoginMvpPresenter<LoginMvpView> provideLoginPresenter(LoginPresenter<LoginMvpView> presenter) {
        return presenter;
    }


    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(MainPresenter<MainMvpView> presenter) {
        return presenter;
    }


    @Provides
    RssAdapter provideRssAdapter() {
        return new RssAdapter(new ArrayList<>());
    }
}