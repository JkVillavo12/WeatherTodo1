package jkvillavo.co.com.weather.ui.splash;

public interface IContractSplash {

    interface View {

        /**
         * Navegar a la actividad de clima
         */
        void nextWeather();

    }

    interface Presenter {

        /**
         * navegar a la siguiente pantalla
         *
         * @param i millis por esperar
         */
        void next(int i);

        /**
         * navegar a la actividad del clima
         */
        void nextWeather();
    }

    interface Model {

        /**
         * Valida lo necesario para navegar a la actividad de clima
         */
        void next();

    }
}
