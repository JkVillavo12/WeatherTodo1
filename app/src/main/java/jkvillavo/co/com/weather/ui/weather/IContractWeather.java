package jkvillavo.co.com.weather.ui.weather;

import android.location.Location;

import jkvillavo.co.com.weather.data.model.Currently;

public interface IContractWeather {

    interface View {

        /**
         * Muestra un snack bar indicando que no hay conexion a internet
         */
        void showSnackBarNetwork();

        /**
         * Muestra un progreso mientras la consulta al ws
         */
        void showProgress();

        /**
         * Oculta el progreso al terminar la consulta al ws
         */
        void hideProgress();

        /**
         * Muestra un mensaje de error cuando ocurrio un error en la consulta al ws
         *
         * @param throwable
         */
        void onResponseFailure(Throwable throwable);

        /**
         * Muestra un mensaje al usuario
         *
         * @param msg mensaje a mostrar
         */
        void showMessage(String msg);

        /**
         * Muestra la informacion del clima, como resultado del consumo del ws
         *
         * @param currently informacion del clima
         * @param timezone  timezone que entrega el servicio
         */
        void showCurrently(Currently currently, String timezone);

        /**
         * Muestra un emptyState cuando no hay informacion del clima
         */
        void showEmpty();

    }

    interface Presenter {

        /**
         * Indica que se iniciará el consumo del ws y da las ordenes necesarias a la vista mientras se realiza dicho proceso
         *
         * @param location localizacion dada por la vista
         */
        void callForecast(Location location);

    }

    interface Model {

        interface OnFinishedListener {

            /**
             * Metodo que se lanza al terminar satisfactoriamente el ws
             *
             * @param currently informacion del clima
             * @param timezone  timezone dado por el ws
             */
            void onFinished(Currently currently, String timezone);

            /**
             * Indica que debe mostrar al usuario el mensaje dado
             *
             * @param msg mensaje a mostrar
             */
            void onMessage(String msg);

            /**
             * Metodo que se lanza cuando ocurre un error no conocido en la consula
             *
             * @param t
             */
            void onFailure(Throwable t);

            /**
             * Cuando necesitamos mostrar datos empty por algun error de key o en el ws
             */
            void onError();
        }

        /**
         * Consumo de ws de la informacion del clima
         *
         * @param loginPresenter listener que responde a los eventos en el consumo
         * @param location       informacion de la localizacion
         */
        void forecast(OnFinishedListener loginPresenter, Location location);

    }
}
