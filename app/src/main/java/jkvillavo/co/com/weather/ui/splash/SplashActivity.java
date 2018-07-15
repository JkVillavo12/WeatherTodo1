package jkvillavo.co.com.weather.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import jkvillavo.co.com.weather.R;
import jkvillavo.co.com.weather.ui.weather.WeatherActivity;

public class SplashActivity extends AppCompatActivity implements
        IContractSplash.View {

    @BindView(R.id.splash_tvPowered)
    TextView splashTvPowered;

    private IContractSplash.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

        presenter = new SplashPresenter(this, getApplicationContext());
    }

    @Override
    protected void onStart() {

        super.onStart();
        presenter.next(2000);

    }

    @Override
    public void nextWeather() {

        Intent intent = new Intent(SplashActivity.this, WeatherActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        /*
         ActivityOptionsCompat options = ActivityOptionsCompat.
         makeSceneTransitionAnimation(this, (View) splashTvPowered,
         getString(R.string.transition_jk));
         startActivity(intent, options.toBundle());
         */
        startActivity(intent);

    }
}
