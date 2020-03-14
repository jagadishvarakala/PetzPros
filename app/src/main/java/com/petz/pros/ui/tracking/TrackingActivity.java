package com.petz.pros.ui.tracking;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import com.petz.pros.ui.bookingdetails.BookingResponse;
import com.petz.pros.ui.main.ui.bookings.BookingsModule;
import com.petz.pros.utils.CommonUtils;
import com.petz.pros.utils.NetworkUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TrackingActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 5445;

    private GoogleMap googleMap;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Marker currentLocationMarker;
    private Location currentLocation;
    private boolean firstTimeFlag = true;
    private PolylineOptions lineOptions = null;
    private TextView serviceDist;
    private final View.OnClickListener clickListener = view -> {
        if (view.getId() == R.id.currentLocationImageButton && googleMap != null && currentLocation != null)
            animateCamera(currentLocation);
    };

    private final LocationCallback mLocationCallback = new LocationCallback() {

        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);
//            if (locationResult.getLastLocation() == null)
//                return;
//            currentLocation = locationResult.getLastLocation();
//            if (firstTimeFlag && googleMap != null) {
//                animateCamera(currentLocation);
//                firstTimeFlag = false;
//            }
            //showMarker(currentLocation);
        }
    };

    private BookingsModule bookingsModule;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        supportMapFragment.getMapAsync(this);

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
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView startService = findViewById(R.id.service_start_time);
        TextView endService = findViewById(R.id.service_End_time);
        TextView serviceActualStart = findViewById(R.id.service_actual_start_time);
        TextView serviceActualEnd = findViewById(R.id.service_actual_End_time);
        serviceDist = findViewById(R.id.service_dist);
        startService.setText("Start Time : "+bookingsModule.getStartTime());
        endService.setText("End Time : "+bookingsModule.getEndTime());
        if(bookingsModule.isServiceStart())
        serviceActualStart.setText("Actual Start Time : "+bookingsModule.getActualStartTime());
        if(bookingsModule.isServiceEnd())
        serviceActualEnd.setText("Actual End Time :"+bookingsModule.getActualEndTime());
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
                ActivityCompat.requestPermissions(TrackingActivity.this,
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
        return new CameraPosition.Builder().target(latLng).zoom(24).build();
    }

    LatLng startLatLong = null;
    private void showMarker(@NonNull LatLng latLng) {
        if(startLatLong == null){
            startLatLong = latLng;
        }
      //  LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        if (currentLocationMarker == null)
            currentLocationMarker = googleMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker()).position(latLng));
        else
            MarkerAnimation.animateMarkerToGB(currentLocationMarker, latLng, new LatLngInterpolator.Spherical());
        lineOptions.add(latLng);
        googleMap.addPolyline(lineOptions);
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(getCameraPositionWithBearing(latLng)));
//        LatLng lastLatLong = currentLocationMarker.getPosition();
//        Location locationA = new Location("Point A");
//        locationA.setLatitude(startLatLong.latitude);
//        locationA.setLongitude(startLatLong.longitude);
//
//        Location locationB = new Location("Point B");
//        locationB.setLatitude(latLng.latitude);
//        locationB.setLongitude(latLng.longitude);

     //   serviceDist.setText("Distance : "+String.valueOf(distance(lastLatLong.latitude,lastLatLong.longitude,latLng.latitude,latLng.longitude)/1000)+ "Km");
        //distance(lastLatLong.latitude,lastLatLong.longitude,latLng.latitude,latLng.longitude);
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

            Call<GetLocationRes> call = api.GET_LOCATION_RES_CALL(bookingsModule.getId() );

            call.enqueue(new Callback<GetLocationRes>() {
                @Override
                public void onResponse(Call<GetLocationRes> call, Response<GetLocationRes> response) {

                    if (response.code() == 200) {
                        if (response.isSuccessful()) {
                            //Dismiss Dialog
                            if(response.body() != null) {
                                if (!TextUtils.isEmpty(response.body().getGeoDataPath())) {
                                    geoLatLongArray.clear();
                                    lineOptions =  new PolylineOptions();
                                    if(response.body().getGeoDataPath().contains(";")) {
                                        String[] parts = response.body().getGeoDataPath().split(";");
                                        for(String latlong : Arrays.asList(parts)){
                                            String[] lat = latlong.split(",");
                                            LatLng latLng = new LatLng(Double.parseDouble(lat[0]),Double.parseDouble(lat[1]));
                                            geoLatLongArray.add(latLng);
                                            showMarker(latLng);
                                        }
                                    }else{
                                        String[] lat = response.body().getGeoDataPath().split(",");
                                        LatLng latLng = new LatLng(Double.parseDouble(lat[0]),Double.parseDouble(lat[1]));
                                        geoLatLongArray.add(latLng);
                                        showMarker(latLng);
                                    }
                                }

                            }
                        }
                    }else if(response.code() == 404){

                    }

                }

                @Override
                public void onFailure(Call<GetLocationRes> call, Throwable t) {
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
            finish();
        }
        return true;
    }
}
