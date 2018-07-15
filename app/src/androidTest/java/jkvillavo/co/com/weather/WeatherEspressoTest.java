package jkvillavo.co.com.weather;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import jkvillavo.co.com.weather.ui.weather.WeatherActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class WeatherEspressoTest {

    @Rule
    public ActivityTestRule<WeatherActivity> mActivityWeather =
            new ActivityTestRule<>(WeatherActivity.class);

    @Test
    public void ensureProgressHide() {
        // Type text and then press the button.
        onView(withId(R.id.weather_progress))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
    }

    @Test
    public void ensureProgressHideAfterClick() {

        onView(withId(R.id.weather_fabRefresh)).perform(click());
        onView(withId(R.id.weather_progress))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));

    }

    @Test
    public void ensureRainText() {

        onView(withId(R.id.weather_fabRefresh)).perform(click());
        onView(withId(R.id.weather_tvRainValue)).
                check(matches(not(withText("-"))));

    }

    @Test
    public void ensureHumidityText() {

        onView(withId(R.id.weather_fabRefresh)).perform(click());
        onView(withId(R.id.weather_tvHumidityValue)).
                check(matches(not(withText("-"))));

    }

}
