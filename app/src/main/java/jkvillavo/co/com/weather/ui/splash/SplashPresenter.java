package jkvillavo.co.com.weather.ui.splash;

import android.content.Context;
import android.os.Handler;

public class SplashPresenter implements IContractSplash.Presenter {

    private IContractSplash.View view;
    private IContractSplash.Model model;
    private Context mContext;

    public SplashPresenter(IContractSplash.View view, Context context) {

        this.view = view;
        this.model = new SplashModel(this, context);
        this.mContext = context;

    }

    @Override
    public void next(int millis) {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            public void run() {

                model.next();

            }
        }, millis);

    }

    @Override
    public void nextWeather() {

        if (view != null) {
            view.nextWeather();
        }

    }
}
