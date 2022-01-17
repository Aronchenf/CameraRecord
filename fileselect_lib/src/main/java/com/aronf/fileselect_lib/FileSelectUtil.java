package com.aronf.fileselect_lib;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class FileSelectUtil {

    public static FileSelectBuilder init(FragmentActivity activity) {
        return new FileSelectBuilder(activity, null);
    }

    public static FileSelectBuilder init(Fragment fragment) {
        return new FileSelectBuilder(null, fragment);
    }
}
