package com.petz.pros.data.network;


import com.petz.pros.data.network.pojo.IsUserExitRequest;
import com.petz.pros.data.network.pojo.IsUserExitResponse;
import com.petz.pros.data.network.pojo.LoginRequest;
import com.petz.pros.data.network.pojo.RegistrationRequest;
import com.petz.pros.ui.bookingdetails.BookingModel;
import com.petz.pros.ui.bookingdetails.BookingResponse;
import com.petz.pros.ui.caretackerlist.CareTackersModel;
import com.petz.pros.ui.caretackerlist.CareTackersRequest;
import com.petz.pros.ui.main.contact.SendMessageRequest;
import com.petz.pros.ui.main.ui.bookings.BookingsModule;
import com.petz.pros.ui.main.ui.bookings.UpdatePayment;
import com.petz.pros.ui.main.upcoming.StartServiceReq;
import com.petz.pros.ui.tracking.GetLocationRes;
import com.petz.pros.ui.tracking.UpdateGeoReq;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    /*
  Retrofit get annotation with our URL
  And our method that will return us the List of User List
  */

    @FormUrlEncoded
    @POST("addUser.php")
    public Call<ResponseBody> registerUser(@Field("name") String name,
                                           @Field("email") String password,
                                           @Field("password") String email, @Field("image") String image);


    @POST("petowner/login")
    public Call<RegistrationRequest> ownerLogin(@Body LoginRequest loginRequest);

    @POST("petcaretaker/login")
    public Call<RegistrationRequest> careTackerLogin(@Body LoginRequest loginRequest);

    @POST("petzpros/IsUserExist")
    public Call<IsUserExitResponse> isUserExit(@Body IsUserExitRequest isUserExitRequest);

    @POST("petowner/register")
    public Call<ResponseBody> petOwnerRegistration(@Body RegistrationRequest request);

    @POST("petcaretaker/register")
    public Call<ResponseBody> petCareTackerRegistration(@Body RegistrationRequest request);

    @POST("petowner/ForgotPassword")
    public Call<ResponseBody> petOwnerForgotPassword(@Body LoginRequest request);

    @POST("petcaretaker/ForgotPassword")
    public Call<ResponseBody> petCareTackerForgotPassword(@Body LoginRequest request);


    @POST("petowner/GetAvailableWalkers")
    public Call<List<CareTackersModel>> availableCareTackers(@Body CareTackersRequest careTackersRequest);

    @POST("petowner/BookingConfirm")
    Call<BookingResponse> bookingConfirm (@Body BookingModel bookingModel);

    @POST("petowner/UpdateProfile")
    public Call<RegistrationRequest> petOwnerProfileUpdate(@Body RegistrationRequest request);

    @POST("petcaretaker/UpdateProfile")
    public Call<RegistrationRequest> petcaretakerProfileUpdate(@Body RegistrationRequest request);

    @GET("petowner/GetPetOwnerBookingHistory?")
    Call<ArrayList<BookingsModule>> getBookings (@Query("id") int userId);

    @GET("petowner/GetPastFutureBookings?")
    Call<ArrayList<ArrayList<BookingsModule>>> getHistory (@Query("id") int userId);

    @GET("petcaretaker/GetNotification?")
    Call<ArrayList<BookingsModule>> getNotifications (@Query("id") int userId);

    @GET("petcaretaker/GetPastFutureHistory?")
    Call<ArrayList<ArrayList<BookingsModule>>> getPastFutureHistory (@Query("id") int userId);

    @POST("petcaretaker/UpdateBookingStatus")
    public Call<ResponseBody> updateBookingStatus (@Body BookingsModule request);

    @POST("petzpros/SendMessage")
    Call<ResponseBody> sendMessage(@Body SendMessageRequest sendMessageRequest);

    @POST("petowner/PaymentSucess")
    Call<ResponseBody> updatePaymentStatus(@Body UpdatePayment bookingsModule);

    @GET("petowner/GetGeoLocation")
    Call<GetLocationRes> GET_LOCATION_RES_CALL (@Query("bookingId") int bookingId);

    @POST("petcaretaker/StartService")
    Call<ResponseBody> START_SERVICE_REQ_CALL (@Body StartServiceReq startServiceReq);

    @POST("petcaretaker/UpdateGeoLocation")
    Call<ResponseBody> UPDATE_LOCATION_REQ_CALL(@Body UpdateGeoReq updateGeoReq);

    @POST("petcaretaker/EndService")
    Call<ResponseBody> END_SERVICE_REQ_CALL (@Body StartServiceReq startServiceReq);
}
