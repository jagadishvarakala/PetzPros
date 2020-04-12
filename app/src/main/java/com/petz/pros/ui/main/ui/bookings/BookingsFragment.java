package com.petz.pros.ui.main.ui.bookings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import com.petz.pros.R;
import com.petz.pros.databinding.FragmentBookingsBinding;
import com.petz.pros.ui.ConfirmationActivity;
import com.petz.pros.ui.PayPalActivity;
import com.petz.pros.ui.base.BaseFragment;
import com.petz.pros.ui.bookingdetails.BookingActivity;
import com.petz.pros.ui.main.ui.bookings.adapter.BookingsAdapter;
import com.petz.pros.ui.tracking.TrackingActivity;
import com.petz.pros.utils.PayPalConfig;

import org.json.JSONException;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.inject.Inject;

import okhttp3.ResponseBody;


public class BookingsFragment extends BaseFragment implements BookingsMvpView {

    @Inject
    BookingsMvpPresenter<BookingsMvpView> bookingMvpPresenter;
    private FragmentBookingsBinding fragmentBookingsBinding;
    private UpdatePayment   updatePayment;
    private int fbBookingId;

    private BookingsAdapter bookingsAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        fragmentBookingsBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_bookings, container, false);
        getActivityComponent().inject(this);
        bookingMvpPresenter.onAttach(this);
        return fragmentBookingsBinding.getRoot();
    }

    @Override
    protected void setUp(View view) {
        updatePayment = new UpdatePayment();
        // bind RecyclerView
        RecyclerView recyclerView = fragmentBookingsBinding.viewEmployees;
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        recyclerView.setHasFixedSize(true);

         bookingsAdapter = new BookingsAdapter();
         bookingsAdapter.setOwner(bookingMvpPresenter.isPetOwner());
         bookingsAdapter.setClickListener(this);
        recyclerView.setAdapter(bookingsAdapter);


    }

    @Override
    public void onResume() {
        super.onResume();
        bookingMvpPresenter.getBookingsDetails();
    }

    @Override
    public void showNoDataFound(String message) {
        fragmentBookingsBinding.nodataFoundText.setText(message);
        updateBooking(new ArrayList<>());
    }

    @Override
    public void updateBooking(ArrayList<BookingsModule> arrayLists) {
        bookingsAdapter.setEmployeeList(arrayLists);
    }

    @Override
    public void onClickPayBtn(BookingsModule module) {
        fbBookingId = module.getId();
        UpdatePayment.BookingDetails bookingDetails = new UpdatePayment.BookingDetails();
        bookingDetails.setAddress(module.getAddress());
        bookingDetails.setBookingStatus(module.isBookingStatus());
        bookingDetails.setCareTackerId(module.getCareTakerID());
        bookingDetails.setId(module.getId());
        bookingDetails.setName(module.getName());
        bookingDetails.setOwnerId(module.getOwnerId());
        bookingDetails.setPaymentStatus(module.isPaymentStatus());
        bookingDetails.setPhone(module.getPhone());
        bookingDetails.setServiceName(module.getServiceName());
        bookingDetails.setServiceRefTypeId(module.getServiceRefTypeId());
        bookingDetails.setTotalAmount(module.getTotalAmount());
        bookingDetails.setCareTaker(module.getCareTacker());
        bookingDetails.setAppointmentDate(module.getAppointmentDate());
        bookingDetails.setStartTime(module.getStartTime());
        bookingDetails.setEndTime(module.getEndTime());
        bookingDetails.setFCMDeviceId(module.getFCMDeviceId());
        updatePayment.setBookingDetails(bookingDetails);
        Intent intent = new Intent(getBaseActivity(), PayPalService.class);

        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

        getBaseActivity().startService(intent);

        getPayment(module.getTotalAmount(),module.getServiceName());
    }

    @Override
    public void onClickTrackBtn(BookingsModule bookingsModule) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("tracking_info",bookingsModule);
        startActivity(new Intent(getBaseActivity(), TrackingActivity.class).putExtras(bundle));
    }

    @Override
    public void onClickStartService(BookingsModule bookingsModule) {

    }

    @Override
    public void paymentDetailsUpdated(ResponseBody responseBody) {
        startActivity(new Intent(getBaseActivity(), ConfirmationActivity.class)
                                .putExtra("PaymentDetails", paymentDetail)
                                .putExtra("PaymentAmount", paymentAmount));
    }

    @Override
    public void onClickServiceStart(BookingsModule bookingsModule) {

    }

    @Override
    public void onClickEndService(BookingsModule bookingsModule) {

    }

    //Paypal intent request code to track onActivityResult method
    public static final int PAYPAL_REQUEST_CODE = 123;
    //Payment Amount
    private String paymentAmount;
    String paymentDetail;
    //Paypal Configuration Object
    private static PayPalConfiguration config = new PayPalConfiguration()
            // Start with mock environment.  When ready, switch to sandbox (ENVIRONMENT_SANDBOX)
            // or live (ENVIRONMENT_PRODUCTION)
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(PayPalConfig.PAYPAL_CLIENT_ID);

    private void getPayment(double totalAmount, String serviceName) {
        paymentAmount = String.valueOf(totalAmount);
        //Creating a paypalpayment
        PayPalPayment payment = new PayPalPayment(new BigDecimal(totalAmount), "USD", serviceName +" Fee",
                PayPalPayment.PAYMENT_INTENT_SALE);

        //Creating Paypal Payment activity intent
        Intent intent = new Intent(getBaseActivity(), PaymentActivity.class);

        //putting the paypal configuration to the intent
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

        //Puting paypal payment to the intent
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);

        //Starting the intent activity for result
        //the request code will be used on the method onActivityResult
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //If the result is from paypal
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PAYPAL_REQUEST_CODE) {

            //If the result is OK i.e. user has not canceled the payment
            if (resultCode == Activity.RESULT_OK) {
                //Getting the payment confirmation
                PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);

                //if confirmation is not null
                if (confirm != null) {
                    try {
                        //Getting the payment details
                        paymentDetail = confirm.toJSONObject().toString(4);
                        Log.i("paymentExample", paymentDetail);
                       UpdatePayment.PaymentDetails details = new UpdatePayment.PaymentDetails();
                        details.setCreatedDateTime(confirm.getProofOfPayment().getCreateTime());
                        //details.setFkBookingId(confirm.getProofOfPayment().getPaymentId());
                        details.setShipping(0.0);
                        details.setSubTotal(Double.parseDouble(paymentAmount));
                        details.setTax(0.0);
                        details.setTotalAmountPaid(Double.parseDouble(paymentAmount));
                        details.setTransactionId(confirm.getProofOfPayment().getTransactionId());
                        details.setFkBookingId(fbBookingId);
                        updatePayment.setPaymentDetails(details);
                        updatePayment.getBookingDetails().setPaymentStatus("accepted");
                        bookingMvpPresenter.storePaymentDetails(updatePayment);
                        //Starting a new activity for the payment details and also putting the payment details with intent
//                        startActivity(new Intent(getBaseActivity(), ConfirmationActivity.class)
//                                .putExtra("PaymentDetails", paymentDetails)
//                                .putExtra("PaymentAmount", paymentAmount));

                    } catch (JSONException e) {
                        Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i("paymentExample", "The user canceled.");
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i("paymentExample", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
            }
        }
    }


}