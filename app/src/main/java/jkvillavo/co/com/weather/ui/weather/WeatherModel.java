package jkvillavo.co.com.weather.ui.weather;

import android.content.Context;
import android.location.Location;

import com.google.gson.Gson;

import org.json.JSONObject;

import jkvillavo.co.com.weather.R;
import jkvillavo.co.com.weather.data.Config;
import jkvillavo.co.com.weather.data.model.ErrorOut;
import jkvillavo.co.com.weather.data.model.ForecastOut;
import jkvillavo.co.com.weather.data.network.DarkSkyApi;
import jkvillavo.co.com.weather.data.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherModel implements IContractWeather.Model {

    private IContractWeather.Presenter presenter;
    private Context mContext;

    public WeatherModel(IContractWeather.Presenter presenter, Context context) {

        this.presenter = presenter;
        this.mContext = context;

    }

    @Override
    public void forecast(final OnFinishedListener loginPresenter, Location location) {

        Call<ForecastOut> call = RetrofitInstance.getRetrofitInstance().create(
                DarkSkyApi.class).forecast(Config.DarkSky.KEY,
                location.getLatitude() + "," +
                        location.getLongitude());

        call.enqueue(new Callback<ForecastOut>() {

            public void onResponse(Call<ForecastOut> call, Response<ForecastOut> response) {

                if (response.body() != null) {
                    ForecastOut forecastOut = response.body();
                    if (forecastOut != null) {
                        loginPresenter.onFinished(forecastOut.getCurrently(), forecastOut.getTimezone());
                    }
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        ErrorOut error = new Gson().fromJson(jObjError.toString(), ErrorOut.class);
                        loginPresenter.onMessage(error.getError());
                        loginPresenter.onError();
                    } catch (Exception e) {
                        loginPresenter.onMessage(mContext.getString(R.string.text_error));
                    }
                }
            }

            @Override
            public void onFailure(Call<ForecastOut> call, Throwable t) {

                loginPresenter.onFailure(t);
            }

        });

    }
}
