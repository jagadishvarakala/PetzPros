package com.petz.pros.ui.tracking;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.petz.pros.R;
import com.petz.pros.data.network.ApiClient;
import com.petz.pros.data.network.ApiInterface;
import com.petz.pros.ui.main.ui.bookings.BookingsModule;
import com.petz.pros.ui.main.upcoming.StartServiceReq;
import com.petz.pros.utils.CommonUtils;
import com.petz.pros.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UpdatingLocationActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 5445;

    private GoogleMap googleMap;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Marker currentLocationMarker;
    private Location currentLocation;
    private boolean firstTimeFlag = true;
    private PolylineOptions lineOptions = null;
    private final View.OnClickListener clickListener = view -> {
        if (view.getId() == R.id.currentLocationImageButton && googleMap != null && currentLocation != null)
            animateCamera(currentLocation);
    };

    private final LocationCallback mLocationCallback = new LocationCallback() {

        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);
            if (locationResult.getLastLocation() == null)
                return;
            currentLocation = locationResult.getLastLocation();
            if (firstTimeFlag && googleMap != null) {
                animateCamera(currentLocation);
                firstTimeFlag = false;
            }
            showMarker(currentLocation);
        }
    };

    private BookingsModule bookingsModule;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_location);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        supportMapFragment.getMapAsync(this);
        findViewById(R.id.currentLocationImageButton).setOnClickListener(clickListener);
        lineOptions = new PolylineOptions();

        bookingsModule = (BookingsModule) getIntent().getSerializableExtra("tracking_info");

        Timer _Request_Trip_Timer = new Timer();
        _Request_Trip_Timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //your code here
                getUpdatedLocation();

            }
        }, 5, 10000);

        Button endBtn = findViewById(R.id.end_service_btn);
        endBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endServiceCall();
            }
        });

        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    private void startCurrentLocationUpdates() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setSmallestDisplacement(100);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(UpdatingLocationActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                return;
            }
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, mLocationCallback, Looper.myLooper());
    }

    private boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int status = googleApiAvailability.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status)
            return true;
        else {
            if (googleApiAvailability.isUserResolvableError(status))
                Toast.makeText(this, "Please Install google play services to use this application", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_DENIED)
                Toast.makeText(this, "Permission denied by uses", Toast.LENGTH_SHORT).show();
            else if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                startCurrentLocationUpdates();
        }
    }

    private void animateCamera(@NonNull Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(getCameraPositionWithBearing(latLng)));
    }

    @NonNull
    private CameraPosition getCameraPositionWithBearing(LatLng latLng) {
        return new CameraPosition.Builder().target(latLng).zoom(16).build();
    }

    private String latLongString = null;
    private void showMarker(@NonNull Location currentLocation) {
        LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        if(latLongString == null){
            latLongString = currentLocation.getLatitude()+","+currentLocation.getLongitude();
        }else if(!TextUtils.isEmpty(latLongString)){
            latLongString = latLongString +";"+ currentLocation.getLatitude()+","+currentLocation.getLongitude();
        }
        if (currentLocationMarker == null)
            currentLocationMarker = googleMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker()).position(latLng));
        else
            MarkerAnimation.animateMarkerToGB(currentLocationMarker, latLng, new LatLngInterpolator.Spherical());
        lineOptions.add(latLng);
        googleMap.addPolyline(lineOptions);
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(getCameraPositionWithBearing(latLng)));
        LatLng lastLatLong = currentLocationMarker.getPosition();
        distance(lastLatLong.latitude,lastLatLong.longitude,latLng.latitude,latLng.longitude);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (fusedLocationProviderClient != null)
            fusedLocationProviderClient.removeLocationUpdates(mLocationCallback);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isGooglePlayServicesAvailable()) {
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
            startCurrentLocationUpdates();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fusedLocationProviderClient = null;
        googleMap = null;
    }

    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    ArrayList<LatLng> geoLatLongArray = new ArrayList<>();

    private void getUpdatedLocation(){
        if ( NetworkUtils.isNetworkConnected(getApplicationContext())) {

            //Creating an object of our api interface
            ApiInterface api = ApiClient.getApiService();
            UpdateGeoReq updateGeoReq = new UpdateGeoReq();
            updateGeoReq.setBookingId(bookingsModule.getId());
            updateGeoReq.setGeoDataPath(latLongString);

            Call<ResponseBody> call = api.UPDATE_LOCATION_REQ_CALL(updateGeoReq);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                    if (response.code() == 200) {
                        if (response.isSuccessful()) {
                            //Dismiss Dialog
                            if(response.body() != null) {


                            }
                        }
                    }else if(response.code() == 404){

                    }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    //Dismiss Dialog

                }
            });
        } else {
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(UpdatingLocationActivity.this, "Internet Connection Not Available", Toast.LENGTH_SHORT).show();
                }
            });


        }
    }

    private void endServiceCall(){
        if ( NetworkUtils.isNetworkConnected(getApplicationContext())) {

            //Creating an object of our api interface
            ApiInterface api = ApiClient.getApiService();
            StartServiceReq updateGeoReq = new StartServiceReq();
            updateGeoReq.setId(bookingsModule.getId());
            updateGeoReq.setBookingStatus(true);
            updateGeoReq.setPaymentStatus(true);
            updateGeoReq.setActualEndTime(CommonUtils.getCurrentDateTime());

            Call<ResponseBody> call = api.END_SERVICE_REQ_CALL(updateGeoReq);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                    if (response.code() == 200) {
                        if (response.isSuccessful()) {
                            //Dismiss Dialog
                            if(response.body() != null) {

                                finish();
                            }
                        }
                    }else if(response.code() == 404){

                    }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    //Dismiss Dialog

                }
            });
        } else {
            Toast.makeText(this, "Internet Connection Not Available", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            aleartDialog();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        aleartDialog();
    }

    private void aleartDialog(){
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Info")
                .setMessage("Are you sure you want to End Service")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        endServiceCall();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

}
