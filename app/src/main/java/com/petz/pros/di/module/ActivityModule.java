package com.petz.pros.di.module;

import android.content.Context;

import com.petz.pros.di.ActivityContext;
import com.petz.pros.di.PerActivity;
import com.petz.pros.ui.bookingdetails.BookingMvpPresenter;
import com.petz.pros.ui.bookingdetails.BookingMvpView;
import com.petz.pros.ui.bookingdetails.BookingPresenter;
import com.petz.pros.ui.caretackerlist.CareTackerListMvpPresenter;
import com.petz.pros.ui.caretackerlist.CareTackerListPresenter;
import com.petz.pros.ui.caretackerlist.CareTackerMvpView;
import com.petz.pros.ui.login.LoginMvpPresenter;
import com.petz.pros.ui.login.LoginMvpView;
import com.petz.pros.ui.login.LoginPresenter;
import com.petz.pros.ui.main.MainMvpPresenter;
import com.petz.pros.ui.main.MainMvpView;
import com.petz.pros.ui.main.MainPresenter;
import com.petz.pros.ui.main.RssAdapter;
import com.petz.pros.ui.main.about.AboutMvpPresenter;
import com.petz.pros.ui.main.about.AboutMvpView;
import com.petz.pros.ui.main.about.AboutPresenter;
import com.petz.pros.ui.main.contact.ContactMvpPresenter;
import com.petz.pros.ui.main.contact.ContactMvpView;
import com.petz.pros.ui.main.contact.ContactPresenter;
import com.petz.pros.ui.main.faq.FaqMvpPresenter;
import com.petz.pros.ui.main.faq.FaqMvpView;
import com.petz.pros.ui.main.faq.FaqPresenter;
import com.petz.pros.ui.main.privacy.PrivacyMvpPresenter;
import com.petz.pros.ui.main.privacy.PrivacyMvpView;
import com.petz.pros.ui.main.privacy.PrivacyPresenter;
import com.petz.pros.ui.main.termsconditions.TermsMvpPresenter;
import com.petz.pros.ui.main.termsconditions.TermsMvpView;
import com.petz.pros.ui.main.termsconditions.TermsPresenter;
import com.petz.pros.ui.main.ui.caretackerprofile.CareTackerProfileMvpPresenter;
import com.petz.pros.ui.main.ui.caretackerprofile.CareTackerProfileMvpView;
import com.petz.pros.ui.main.ui.caretackerprofile.CareTackerProfilePresenter;
import com.petz.pros.ui.main.ui.dashboard.DashBoardMvpPresenter;
import com.petz.pros.ui.main.ui.dashboard.DashBoardMvpView;
import com.petz.pros.ui.main.ui.dashboard.DashBoardPresenter;
import com.petz.pros.ui.main.ui.more.MoreMvpPresenter;
import com.petz.pros.ui.main.ui.more.MoreMvpView;
import com.petz.pros.ui.main.ui.more.MorePresenter;
import com.petz.pros.ui.main.ui.past.PastServicesMvpPresenter;
import com.petz.pros.ui.main.ui.past.PastServicesMvpView;
import com.petz.pros.ui.main.ui.past.PastServicesPresenter;
import com.petz.pros.ui.main.ui.pending.PendingServicesMvpPresenter;
import com.petz.pros.ui.main.ui.pending.PendingServicesMvpView;
import com.petz.pros.ui.main.ui.pending.PendingServicesPresenter;
import com.petz.pros.ui.main.ui.profile.ProfileMvpPresenter;
import com.petz.pros.ui.main.ui.profile.ProfileMvpView;
import com.petz.pros.ui.main.ui.profile.ProfilePresenter;
import com.petz.pros.ui.main.ui.home.HomeMvpPresenter;
import com.petz.pros.ui.main.ui.home.HomeMvpView;
import com.petz.pros.ui.main.ui.home.HomePresenter;
import com.petz.pros.ui.main.ui.bookings.BookingsMvpPresenter;
import com.petz.pros.ui.main.ui.bookings.BookingsMvpView;
import com.petz.pros.ui.main.ui.bookings.BookingsPresenter;
import com.petz.pros.ui.main.ui.history.HistoryMvpPresenter;
import com.petz.pros.ui.main.ui.history.HistoryMvpView;
import com.petz.pros.ui.main.ui.history.HistoryPresenter;
import com.petz.pros.ui.main.ui.rejected.RejectedServicesMvpPresenter;
import com.petz.pros.ui.main.ui.rejected.RejectedServicesMvpView;
import com.petz.pros.ui.main.ui.rejected.RejectedServicesPresenter;
import com.petz.pros.ui.main.upcoming.UpcomingServicesMvpPresenter;
import com.petz.pros.ui.main.upcoming.UpcomingServicesMvpView;
import com.petz.pros.ui.main.upcoming.UpcomingServicesPresenter;
import com.petz.pros.ui.orderdetails.OrderDetailsMvpPresenter;
import com.petz.pros.ui.orderdetails.OrderDetailsMvpView;
import com.petz.pros.ui.orderdetails.OrderDetailsPresenter;
import com.petz.pros.ui.registration.personaldetails.PersonalMvpPresenter;
import com.petz.pros.ui.registration.personaldetails.PersonalMvpView;
import com.petz.pros.ui.registration.personaldetails.PersonalPresenter;
import com.petz.pros.ui.splash.SplashMvpPresenter;
import com.petz.pros.ui.splash.SplashMvpView;
import com.petz.pros.ui.splash.SplashPresenter;
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
    HomeMvpPresenter<HomeMvpView> provideHomePresenter(HomePresenter<HomeMvpView> presenter) {
        return presenter;
    }

    @Provides
    ProfileMvpPresenter<ProfileMvpView> provideProfilePresenter(ProfilePresenter<ProfileMvpView> presenter) {
        return presenter;
    }

    @Provides
    BookingsMvpPresenter<BookingsMvpView> provideBookingsPresenter(BookingsPresenter<BookingsMvpView> presenter) {
        return presenter;
    }

    @Provides
    HistoryMvpPresenter<HistoryMvpView> provideHistoryPresenter(HistoryPresenter<HistoryMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    DashBoardMvpPresenter<DashBoardMvpView> provideDashBoardPresenter(DashBoardPresenter<DashBoardMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    CareTackerProfileMvpPresenter<CareTackerProfileMvpView> provideCareTackerProfilePresenter (CareTackerProfilePresenter<CareTackerProfileMvpView> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    PendingServicesMvpPresenter<PendingServicesMvpView> providePendingServicesPresenter (PendingServicesPresenter<PendingServicesMvpView> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    RejectedServicesMvpPresenter<RejectedServicesMvpView> provideRejectedServicesPresenter (RejectedServicesPresenter<RejectedServicesMvpView> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    PastServicesMvpPresenter<PastServicesMvpView> providePastServicesPresenter (PastServicesPresenter<PastServicesMvpView> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    UpcomingServicesMvpPresenter<UpcomingServicesMvpView> provideUpcomingServicesPresenter (UpcomingServicesPresenter<UpcomingServicesMvpView> presenter){
        return presenter;
    }

    @Provides
    PersonalMvpPresenter<PersonalMvpView> providePersonalPresenter(PersonalPresenter<PersonalMvpView> presenter) {
        return presenter;
    }
    @Provides
    RssAdapter provideRssAdapter() {
        return new RssAdapter(new ArrayList<>());
    }

    @Provides
    @PerActivity
    CareTackerListMvpPresenter<CareTackerMvpView> provideCareTackerListPresenter(CareTackerListPresenter<CareTackerMvpView> presenter) {
        return presenter;
    }
    @Provides
    @PerActivity
    BookingMvpPresenter<BookingMvpView> provideBookingPresenter(BookingPresenter<BookingMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView> provideSplashPresenter(SplashPresenter<SplashMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    MoreMvpPresenter<MoreMvpView> provideMorePresenter( MorePresenter< MoreMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    AboutMvpPresenter<AboutMvpView> provideAboutPresenter(AboutPresenter< AboutMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    ContactMvpPresenter<ContactMvpView> provideContactPresenter(ContactPresenter<ContactMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    FaqMvpPresenter<FaqMvpView> provideFaqPresenter(FaqPresenter< FaqMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    PrivacyMvpPresenter<PrivacyMvpView> providePrivacyPresenter(PrivacyPresenter< PrivacyMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    TermsMvpPresenter<TermsMvpView> provideTermsPresenter(TermsPresenter<TermsMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    OrderDetailsMvpPresenter<OrderDetailsMvpView> provideOrderDetailsPresenter(OrderDetailsPresenter<OrderDetailsMvpView> presenter){
        return presenter;
    }
}