package com.ai.customcamera.camerax;

import androidx.camera.core.ImageCapture;

public class CameraConfig {
    //1.多媒体模式
    public static int MEDIA_MODE_PHOTO = 1;//仅仅拍照
    public static int MEDIA_MODE_VIDEO = 2;//仅仅视频
    public static int MEDIA_MODE_ALL = 3;//拍照视频都可以

    //闪光灯模式
    public static int CAMERA_FLASH_AUTO = ImageCapture.FLASH_MODE_AUTO;
    public static int CAMERA_FLASH_ON = ImageCapture.FLASH_MODE_ON;
    public static int CAMERA_FLASH_OFF = ImageCapture.FLASH_MODE_OFF;
    public static int CAMERA_FLASH_ON_SCREEN = 0x01;

    public int flashMode;
    public int mediaMode;

   public CameraConfig(Builder builder) {
        flashMode = builder.flashMode;
        mediaMode = builder.mediaMode;
    }

    public static class Builder {
        int flashMode = CAMERA_FLASH_OFF;
        int mediaMode = MEDIA_MODE_PHOTO;

        public Builder flashMode(int flashMode) {
            this.flashMode = flashMode;
            return this;
        }

        public Builder mediaMode(int mediaMode) {
            this.mediaMode = mediaMode;
            return this;
        }

        public CameraConfig build() {
            return new CameraConfig(this);
        }
    }
}
