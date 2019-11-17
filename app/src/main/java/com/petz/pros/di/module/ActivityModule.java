package com.petz.cleancode.di.module;

import android.content.Context;

import com.petz.cleancode.di.ActivityContext;
import com.petz.cleancode.di.PerActivity;
import com.petz.cleancode.ui.login.LoginMvpPresenter;
import com.petz.cleancode.ui.login.LoginMvpView;
import com.petz.cleancode.ui.login.LoginPresenter;
import com.petz.cleancode.ui.main.MainMvpPresenter;
import com.petz.cleancode.ui.main.MainMvpView;
import com.petz.cleancode.ui.main.MainPresenter;
import com.petz.cleancode.ui.main.RssAdapter;
import com.petz.cleancode.utils.rx.AppSchedulerProvider;
import com.petz.cleancode.utils.rx.SchedulerProvider;

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