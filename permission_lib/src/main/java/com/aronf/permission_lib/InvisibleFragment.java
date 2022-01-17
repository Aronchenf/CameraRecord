package com.aronf.permission_lib;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class InvisibleFragment extends Fragment {

    private PermissionBuilder builder;
    private static final int REQUEST_CODE = 0x01;
    protected static final int GO_TO_SETTINGS = 0x02;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void requestPermission(PermissionBuilder permissionBuilder, String[] permissions) {
        builder = permissionBuilder;
        requestPermissions(permissions, REQUEST_CODE);
    }

    public void requestAgain(String[] permissions) {
        requestPermissions(permissions, REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            Set<String> granted = new HashSet<>();
            Set<String> denied = new HashSet<>();
            builder.neverGrantedPermissions.clear();
            for (int i = 0; i < permissions.length; i++) {
                Log.e("TAG", "onRequestPermissionsResult: " + permissions.length);
                String permission = permissions[i];
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    granted.add(permission);
                } else {
                    boolean shouldShowRationale = shouldShowRequestPermissionRationale(permission);
                    if (shouldShowRationale) {
                        denied.add(permission);
                    } else {
                        builder.neverGrantedPermissions.add(permission);
                    }
                }
            }
            if (builder != null) {
                builder.callBack.result(denied.isEmpty(), granted, denied);
            }
            assert builder != null;
            if (!builder.neverGrantedPermissions.isEmpty()) {
                builder.showDialog();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GO_TO_SETTINGS) {
            requestAgain(builder.neverGrantedPermissions.toArray(new String[0]));
        }
    }


}
