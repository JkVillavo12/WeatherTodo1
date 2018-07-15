package jkvillavo.co.com.weather.utils;

import android.content.Context;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

import jkvillavo.co.com.weather.R;

/**
 * This class encapsulates all logic regarding with grant permissions for the app
 */

public class PermissionsHandler {

    private Context context;
    private String msgDeniedPermissions;

    public PermissionsHandler(Context context) {

        this.context = context;
        this.msgDeniedPermissions = context.getString(R.string.msg_permissions);
    }

    public void check(final PermissionCheckerListener permissionCheckerListener, final String... permissions) {

        if (TedPermission.isGranted(context, permissions)) {
            permissionCheckerListener.isAlreadyGranted(permissions);
            return;
        }

        TedPermission.with(context)
                .setPermissionListener(new PermissionListener() {

                    @Override
                    public void onPermissionGranted() {

                        permissionCheckerListener.onGranted(permissions);
                    }

                    @Override
                    public void onPermissionDenied(ArrayList<String> deniedPermissions) {

                        permissionCheckerListener.onDenied(deniedPermissions);
                    }


                })
                .setDeniedMessage(msgDeniedPermissions)
                .setPermissions(permissions)
                .check();

    }

    public interface PermissionCheckerListener {

        void onGranted(String[] permissions);

        void onDenied(ArrayList<String> deniedPermissions);

        void isAlreadyGranted(String[] permissions);
    }
}
