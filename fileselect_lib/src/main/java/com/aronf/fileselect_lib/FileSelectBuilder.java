package com.aronf.fileselect_lib;

import android.app.Activity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class FileSelectBuilder {

    private FragmentActivity mActivity;
    private Fragment mFragment;

    public FileSelectBuilder(FragmentActivity activity, Fragment fragment) {
        this.mActivity = activity;
        this.mFragment = fragment;
        if (activity == null) {
            this.mActivity = fragment.getActivity();
        }
    }




}
