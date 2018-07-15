package jkvillavo.co.com.weather.utils;

public class Utils {

    /**
     * Obtiene el dato de la temperatura como string y sin decimales
     *
     * @param temperature temperatura dada
     * @return string de la temperatura
     */
    public static String getTemperatureRound(Double temperature) {

        if (temperature == null) return "";
        return String.valueOf(Math.round(temperature));

    }

    /**
     * Obtiene el dato de porcentaje a mostrar, como un string
     *
     * @param data dato de porcentaje
     * @return string con el porcentaje
     */
    public static String getPercentString(Double data) {

        if (data == null) {
            return "0";
        }
        return String.valueOf(Math.round(data * 100));

    }
}
