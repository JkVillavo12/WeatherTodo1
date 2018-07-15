package jkvillavo.co.com.weather;

import org.junit.Test;

import jkvillavo.co.com.weather.utils.Utils;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UtilsUnitTest {

    @Test
    public void getTemperature_isCorrect1() {
        assertEquals("72", Utils.getTemperatureRound(72.2));
    }

    @Test
    public void getTemperature_isCorrect2() {
        assertEquals("72", Utils.getTemperatureRound(71.8));
    }

    @Test
    public void getPercent_isCorrect1() {
        assertEquals("72", Utils.getPercentString(0.72));
    }

    @Test
    public void getPercent_isNull() {
        assertEquals("0", Utils.getPercentString(null));
    }

}