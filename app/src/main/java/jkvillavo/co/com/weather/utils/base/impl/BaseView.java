package jkvillavo.co.com.weather.utils.base.impl;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import jkvillavo.co.com.weather.R;
import jkvillavo.co.com.weather.utils.base.IBaseView;

public class BaseView extends AppCompatActivity implements IBaseView {

    @Override
    public void showToastMessage(String message) {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    /**
     * show a snackBar, its configure the message, action and if it has intent, its start the intent
     *
     * @param coordinatorLayout the coordinatorLayout in Activity
     * @param message           message to show
     * @param action            action text to show
     * @param intent            intent to start
     */
    @Override
    public void showSnackBar(CoordinatorLayout coordinatorLayout, String message, String action, final Intent intent) {

        final Snackbar snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG);

        snackbar.setAction(action, new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (intent != null) {
                    startActivity(intent);
                }
                snackbar.dismiss();
            }
        });

        snackbar.show();

    }

    public void onResponseFailure(Throwable throwable) {

        showMessage(throwable.getLocalizedMessage(), false);

    }

    public void showMessage(String msg, final boolean settings) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(getString(R.string.title_activity_weather));
        alertDialogBuilder
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton(R.string.common_accept,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                                if (settings) {
                                    Intent intent = new Intent();
                                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                                    intent.setData(uri);
                                    startActivity(intent);
                                }
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
}
