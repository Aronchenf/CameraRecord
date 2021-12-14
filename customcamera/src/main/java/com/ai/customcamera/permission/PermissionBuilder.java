package com.ai.customcamera.permission;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PermissionBuilder {

    private static final String FRAGMENT_TAG = "InvisibleFragment";
    private static final String DIALOG_TAG = "PermissionDialog";

    private FragmentActivity mActivity;
    private Fragment mFragment;
    protected PermissionCallBack callBack;

    protected Set<String> grantedPermissions;
    protected Set<String> deniedPermissions;
    protected Set<String> neverGrantedPermissions=new HashSet<>();

    protected PermissionDialog mDialog;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public PermissionBuilder(FragmentActivity activity, Fragment fragment, List<String> permissions) {
        this.mActivity = activity;
        this.mFragment = fragment;
        if (activity == null && fragment != null) {
            this.mActivity = fragment.getActivity();
        }
        initPermissionDialog();
        requestPermissions(permissions);
    }

    private void initPermissionDialog() {
        mDialog = new PermissionDialog()
                .setTitle("")
                .setMessage("")
                .setLeftText("")
                .setRightText("确定")
                .setOnClickListener(new PermissionDialogListener() {
                    @Override
                    public void onPositiveButton() {
                        go2Settings();
                    }

                    @Override
                    public void onNegativeButton() {
                        mActivity.finish();
                    }
                });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestPermissions(List<String> permissions) {
        List<String> permissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(mActivity, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }

        String[] permissionAArray = permissionList.toArray(new String[permissionList.size()]);
        if (!permissionList.isEmpty()) {
            getFragment().requestPermission(PermissionBuilder.this, permissionAArray);
        }
    }

    private FragmentManager getFragmentManager() {
        FragmentManager fragmentManager;
        if (mFragment != null) {
            fragmentManager = mFragment.getFragmentManager();
        } else {
            fragmentManager = mActivity.getSupportFragmentManager();
        }
        return fragmentManager;
    }

    private InvisibleFragment getFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        if (fragment != null) {
            return (InvisibleFragment) fragment;
        } else {
            InvisibleFragment invisibleFragment = new InvisibleFragment();
            fragmentManager.beginTransaction().add(invisibleFragment, FRAGMENT_TAG).commitNowAllowingStateLoss();
            return invisibleFragment;
        }
    }

    /**
     * 设置禁用权限后手动请求自定义对话框
     * @return
     */
    public PermissionBuilder setCustomDialog() {
        return setCustomDialog("");
    }

    public PermissionBuilder setCustomDialog(String title) {
        return setCustomDialog(title, "");
    }

    public PermissionBuilder setCustomDialog(String title, String message) {
        return setCustomDialog(title, message, "");
    }

    public PermissionBuilder setCustomDialog(String title, String message, String leftText) {
        return setCustomDialog(title, message, leftText, "");
    }

    public PermissionBuilder setCustomDialog(String title, String message, String leftText, String rightText) {
        return setCustomDialog(title, message, leftText, rightText, null);
    }

    public PermissionBuilder setCustomDialog(String title, String message, String leftText, String rightText, PermissionDialogListener listener) {
        mDialog.setTitle(title)
                .setMessage(message)
                .setLeftText(leftText)
                .setRightText(rightText)
                .setOnClickListener(listener);
        return this;
    }

    public void showDialog() {
        mDialog.show(getFragmentManager(), DIALOG_TAG);
    }

    private void go2Settings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", mActivity.getPackageName(), null);
        intent.setData(uri);
        getFragment().startActivityForResult(intent, InvisibleFragment.GO_TO_SETTINGS);
    }

    public void result(PermissionCallBack callBack) {
        this.callBack = callBack;
    }

}
