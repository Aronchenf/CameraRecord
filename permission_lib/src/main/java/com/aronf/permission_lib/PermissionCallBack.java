package com.aronf.permission_lib;

import java.util.List;
import java.util.Set;

public interface PermissionCallBack {
    void result(boolean isAllGranted, Set<String> grantedList, Set<String> deniedList);
}
