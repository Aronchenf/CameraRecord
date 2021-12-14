package com.ai.customcamera.permission;

import android.app.Activity;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.M)
public class PermissionCollection {

    private FragmentActivity mActivity;
    private Fragment mFragment;

    public PermissionCollection(FragmentActivity activity) {
        this.mActivity = activity;
    }

    public PermissionCollection(Fragment fragment) {
        this.mFragment = fragment;
    }


    public PermissionBuilder permissions(String... permissions) {
        return permissions(new ArrayList<>(Arrays.asList(permissions)));
    }

    public PermissionBuilder permissions(List<String> permissions) {
        return new PermissionBuilder(mActivity, mFragment, permissions);
    }
}
