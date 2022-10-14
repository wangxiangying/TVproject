package com.tools.tvproject.base.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FileUtils {

    public static File getAppFolder() {
        if (!isSDCardAvailable()) {
            return null;
        }
        String folderName = "fanzhe";
        File folder = new File(Environment.getExternalStorageDirectory(), folderName);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return folder;
    }

    public static void deleteFile(File file) {
        if (file == null) {
            return;
        }
        if (file.isFile() && file.exists()) {
            file.delete();
            return;
        }
        if (file.isDirectory()) {
            File[] childFile = file.listFiles();
            if (childFile == null || childFile.length == 0) {
                file.delete();
                return;
            }
            for (File f : childFile) {
                deleteFile(f);
            }
            file.delete();
        }
    }

    /**
     * 检测SD卡是否可用
     *
     * @return True if SD card is can be written, false otherwise.
     */
    public static boolean isSDCardAvailable() {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
            Log.v("FileUtils","SD卡不可用");
            return false;
        }
        return true;
    }

    public static File createPhotoSavedFile() {
        File folder = getAppFolder();
        if (folder == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS", Locale.CHINA);
        String fname = sdf.format(new Date()) + ".jpg";
        return new File(folder, fname);
    }

}
