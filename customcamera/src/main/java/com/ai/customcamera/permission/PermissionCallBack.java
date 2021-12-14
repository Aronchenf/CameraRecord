package com.ai.customcamera.permission;

import java.util.List;
import java.util.Set;

public interface PermissionCallBack {
    void result(boolean isAllGranted, Set<String> grantedList, Set<String> deniedList);
}
