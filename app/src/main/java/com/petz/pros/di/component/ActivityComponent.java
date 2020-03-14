package com.petz.pros.di.component;


import com.petz.pros.di.PerActivity;
import com.petz.pros.di.module.ActivityModule;
import com.petz.pros.ui.bookingdetails.BookingActivity;
import com.petz.pros.ui.caretackerlist.PetCareTackerListActivity;
import com.petz.pros.ui.forgotpassword.ForgotPasswordActivity;
import com.petz.pros.ui.login.LoginActivity;
import com.petz.pros.ui.main.NavigationActivity;
import com.petz.pros.ui.main.about.AboutActivity;
import com.petz.pros.ui.main.contact.ContactActivity;
import com.petz.pros.ui.main.faq.FaqActivity;
import com.petz.pros.ui.main.privacy.PrivacyActivity;
import com.petz.pros.ui.main.termsconditions.TermsActivity;
import com.petz.pros.ui.main.ui.caretackerprofile.CareTackerProfileActivity;
import com.petz.pros.ui.main.ui.contact.ContactFragment;
import com.petz.pros.ui.main.ui.dashboard.DashBoardActivity;
import com.petz.pros.ui.main.ui.more.MoreActivity;
import com.petz.pros.ui.main.ui.past.PastServicesActivity;
import com.petz.pros.ui.main.ui.pending.PendingServicesActivity;
import com.petz.pros.ui.main.ui.profile.ProfileFragment;
import com.petz.pros.ui.main.ui.home.HomeFragment;
import com.petz.pros.ui.main.ui.bookings.BookingsFragment;
import com.petz.pros.ui.main.ui.history.HistoryFragment;
import com.petz.pros.ui.main.ui.rejected.RejectedServicesActivity;
import com.petz.pros.ui.main.upcoming.UpcomingServicesActivity;
import com.petz.pros.ui.orderdetails.OrderDetailsActivity;
import com.petz.pros.ui.registration.OwnerRegistrationActivity;
import com.petz.pros.ui.registration.caretacker.CareTackerRegistrationActivity;
import com.petz.pros.ui.registration.personaldetails.PersonalDetailsFragment;
import com.petz.pros.ui.splash.SplashActivity;
import com.petz.pros.ui.usertype.UserTypeActivity;

import dagger.Component;

/**
 * Created on : Jan 19, 2019
 * Author     : AndroidWave
 * Email    : info@androidwave.com
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {


    void inject(SplashActivity splashActivity);

    void inject(LoginActivity loginActivity);

    void inject(UserTypeActivity userTypeActivity);

    void inject(OwnerRegistrationActivity ownerRegistrationActivity);

    void inject(CareTackerRegistrationActivity careTackerRegistrationActivity);

    void inject(ForgotPasswordActivity forgotPasswordActivity);

    void inject(NavigationActivity navigationActivity);

    void inject(HomeFragment homeFragment);

    void inject(ProfileFragment profileFragment);

    void inject(PersonalDetailsFragment detailsFragment);

    void inject(PetCareTackerListActivity petCareTackerListActivity);

    void inject(BookingActivity bookingActivity);

    void inject(BookingsFragment bookingsFragment);

    void inject(HistoryFragment historyFragment);

    void inject(DashBoardActivity dashBoardActivity);

    void inject(CareTackerProfileActivity careTackerProfileActivity);

    void inject(PendingServicesActivity pendingServicesActivity);

    void inject(RejectedServicesActivity rejectedServicesActivity);

    void inject(PastServicesActivity pastServicesActivity);

    void inject(UpcomingServicesActivity upcomingServicesActivity);

    void inject(MoreActivity moreActivity);

    void inject(AboutActivity aboutActivity);

    void inject(ContactActivity contactActivity);

    void inject(FaqActivity faqActivity);

    void inject(PrivacyActivity privacyActivity);

    void inject(TermsActivity termsActivity);

    void inject(ContactFragment contactFragment);

    void inject(OrderDetailsActivity orderDetailsActivity);
}