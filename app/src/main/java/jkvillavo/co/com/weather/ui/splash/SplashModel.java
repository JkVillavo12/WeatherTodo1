package jkvillavo.co.com.weather.ui.splash;

import android.Manifest;
import android.content.Context;

import java.util.ArrayList;

import jkvillavo.co.com.weather.utils.PermissionsHandler;

public class SplashModel implements IContractSplash.Model {

    private IContractSplash.Presenter presenter;
    private Context mContext;

    public SplashModel(IContractSplash.Presenter presenter, Context contex) {

        this.presenter = presenter;
        this.mContext = contex;

    }

    @Override
    public void next() {

        new PermissionsHandler(mContext).check(new PermissionsHandler.PermissionCheckerListener() {

                                                   @Override
                                                   public void onGranted(String[] permissions) {

                                                       presenter.nextWeather();
                                                   }

                                                   @Override
                                                   public void onDenied(ArrayList<String> deniedPermissions) {

                                                       presenter.nextWeather();

                                                   }

                                                   @Override
                                                   public void isAlreadyGranted(String[] permissions) {

                                                       presenter.nextWeather();

                                                   }

                                               },
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION);
    }

}
