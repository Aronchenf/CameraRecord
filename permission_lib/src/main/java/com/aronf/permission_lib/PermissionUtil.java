package com.aronf.permission_lib;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.Arrays;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.M)
public class PermissionUtil {

    public static void checkPermissions(Context context, PermissionCheckCallBack callBack, String... permissions) {
        List<String> list = Arrays.asList(permissions.clone());
        checkPermissions(context, list, callBack);
    }

    public static void checkPermissions(Context context, List<String> permissions, PermissionCheckCallBack callBack) {

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                callBack.result(false);
                return;
            }else {
                callBack.result(true);
            }
        }
    }

    public static PermissionCollection init(AppCompatActivity activity) {
        return new PermissionCollection(activity);
    }

    public static PermissionCollection init(Fragment fragment) {
        return new PermissionCollection(fragment);
    }


}
