package com.ai.customcamera.permission;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

@RequiresApi(api = Build.VERSION_CODES.M)
public class PermissionUtil {

    public static boolean hasPermissions(Context context, String... permissions) {
        if (isBelowM()) {
            return true;
        }

        if (context == null) {
            throw new IllegalArgumentException("null context");
        }

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public static PermissionCollection init(FragmentActivity activity) {
        return new PermissionCollection(activity);
    }

    public static PermissionCollection init(Fragment fragment) {
        return new PermissionCollection(fragment);
    }


    private static boolean isBelowM() {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M;
    }
}
