package jkvillavo.co.com.weather.utils.base;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;

public interface IBaseView {

    void onResponseFailure(Throwable throwable);

    void showToastMessage(String message);

    void showSnackBar(CoordinatorLayout coordinatorLayout, String message, String action, Intent intent);

    void showMessage(String msg, boolean settings);

}
