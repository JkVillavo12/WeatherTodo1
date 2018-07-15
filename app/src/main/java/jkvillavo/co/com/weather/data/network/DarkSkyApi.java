package jkvillavo.co.com.weather.data.network;

import jkvillavo.co.com.weather.data.model.ForecastOut;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DarkSkyApi {

    @GET("forecast/{key}/{latlong}")
    Call<ForecastOut> forecast(
            @Path("key") String key,
            @Path("latlong") String latlong);

}
