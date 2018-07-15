package jkvillavo.co.com.weather.ui.weather;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import jkvillavo.co.com.weather.R;
import jkvillavo.co.com.weather.data.model.Currently;
import jkvillavo.co.com.weather.utils.Utils;
import jkvillavo.co.com.weather.utils.base.impl.BaseView;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

public class WeatherActivity extends BaseView implements IContractWeather.View {

    @BindView(R.id.weather_fabRefresh)
    FloatingActionButton weatherFabRefresh;
    @BindView(R.id.weather_tvCity)
    TextView weatherTvCity;
    @BindView(R.id.weather_tvValue)
    TextView weatherTvValue;
    @BindView(R.id.weather_tvRainValue)
    TextView weatherTvRainValue;
    @BindView(R.id.weather_tvHumidityValue)
    TextView weatherTvHumidityValue;
    @BindView(R.id.weather_tvSummary)
    TextView weatherTvSummary;
    @BindView(R.id.weather_coordinator)
    CoordinatorLayout weatherCoordinator;
    @BindView(R.id.weather_progress)
    ProgressBar weatherProgress;

    private IContractWeather.Presenter presenter;

    private LocationRequest mLocationRequest;
    public static final int REQUEST_FINE_LOCATION = 100;
    //private long UPDATE_INTERVAL = 10 * 1000;  /* 10 secs */
    //private long FASTEST_INTERVAL = 2000; /* 2 sec */

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

        initLocation();

    }

    @Override
    protected void onStart() {

        super.onStart();
        presenter = new WeatherPresenter(this, getApplicationContext());
        showEmpty();
        refresh(null);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @SuppressLint("MissingPermission")
    private void initLocation() {

        // Create the location request to start receiving updates
        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        //mLocationRequest.setInterval(UPDATE_INTERVAL);
        //mLocationRequest.setFastestInterval(FASTEST_INTERVAL);

        // Create LocationSettingsRequest object using location request
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        LocationSettingsRequest locationSettingsRequest = builder.build();

        // Check whether location settings are satisfied
        // https://developers.google.com/android/reference/com/google/android/gms/location/SettingsClient
        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        settingsClient.checkLocationSettings(locationSettingsRequest);

        // new Google API SDK v11 uses getFusedLocationProviderClient(this)
        getFusedLocationProviderClient(this).requestLocationUpdates(mLocationRequest, new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        // do work here
                        onLocationChanged(locationResult.getLastLocation());
                    }
                },
                Looper.myLooper());

    }

    public void onLocationChanged(Location location) {
        presenter.callForecast(location);
    }

    @SuppressLint("MissingPermission")
    public void getLastLocation() {

        // Get last known recent location using new Google Play Services SDK (v11+)
        FusedLocationProviderClient locationClient = getFusedLocationProviderClient(this);

        locationClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // GPS location can be null if GPS is switched off
                        if (location != null) {
                            onLocationChanged(location);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("MapDemoActivity", "Error trying to get last GPS location");
                        e.printStackTrace();
                    }
                });
    }

    @Override
    public void showSnackBarNetwork() {

        Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
        showSnackBar(weatherCoordinator,
                getString(R.string.msg_notNetwork), getString(R.string.common_configure),
                intent);

    }

    @Override
    public void showProgress() {

        weatherProgress.setVisibility(View.VISIBLE);
        weatherFabRefresh.setEnabled(false);
        weatherTvCity.setText(R.string.msg_loading);

    }

    @Override
    public void hideProgress() {

        weatherProgress.setVisibility(View.GONE);
        weatherFabRefresh.setEnabled(true);
        weatherTvCity.setText("");

    }

    @Override
    public void onResponseFailure(Throwable throwable) {

        super.onResponseFailure(throwable);

    }

    @Override
    public void showMessage(String msg) {

        super.showMessage(msg, false);

    }

    @Override
    public void showCurrently(Currently currently, String timezone) {

        weatherTvCity.setText(timezone);
        weatherTvSummary.setText(currently.getSummary());
        weatherTvHumidityValue.setText(getString(R.string.text_percent, Utils.getPercentString(currently.getHumidity())));
        weatherTvRainValue.setText(getString(R.string.text_percent, Utils.getPercentString(currently.getPrecipProbability())));
        weatherTvValue.setText(getString(R.string.text_degress, Utils.getTemperatureRound(currently.getTemperature())));

    }

    @Override
    public void showEmpty() {

        weatherTvCity.setText(getString(R.string.msg_notInfo));
        weatherTvSummary.setText("-");
        weatherTvHumidityValue.setText("-");
        weatherTvRainValue.setText("-");
        weatherTvValue.setText("-");

    }

    public void refresh(View view) {
        if (checkPermissions()) {
            getLastLocation();
        }
    }

    private boolean checkPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            requestPermissions();
            return false;
        }
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                REQUEST_FINE_LOCATION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLastLocation();
                } else {
                    super.showMessage(getString(R.string.msg_permissionsSettings), true);
                }
                return;
            }

        }
    }
}
