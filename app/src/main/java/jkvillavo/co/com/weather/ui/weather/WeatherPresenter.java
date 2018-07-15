package jkvillavo.co.com.weather.ui.weather;

import android.content.Context;
import android.location.Location;

import jkvillavo.co.com.weather.data.model.Currently;
import jkvillavo.co.com.weather.utils.NetworkHelper;

public class WeatherPresenter implements IContractWeather.Presenter,
        IContractWeather.Model.OnFinishedListener {

    private IContractWeather.View view;
    private IContractWeather.Model model;
    private Context mContext;

    public WeatherPresenter(IContractWeather.View view, Context context) {

        this.view = view;
        this.model = new WeatherModel(this, context);
        this.mContext = context;

    }

    @Override
    public void callForecast(Location location) {

        if (view != null) {
            if (NetworkHelper.getInstance().isNetworkAvailable((Context) view)) {
                view.showProgress();
                model.forecast(this, location);
            } else {
                view.showSnackBarNetwork();
            }
        }

    }

    @Override
    public void onError() {

        if (view != null) {
            view.hideProgress();
            view.showEmpty();
        }
    }

    @Override
    public void onFinished(Currently currently, String timezone) {

        if (view != null) {
            view.hideProgress();
            view.showCurrently(currently, timezone);
        }

    }

    @Override
    public void onMessage(String msg) {

        if (view != null) {
            view.hideProgress();
            view.showMessage(msg);
        }

    }

    @Override
    public void onFailure(Throwable t) {

        if (view != null) {
            view.hideProgress();
            view.onResponseFailure(t);
        }

    }
}
