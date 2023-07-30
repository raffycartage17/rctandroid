package com.racartech.library.rctandroid.hardware;

import android.content.Context;
import android.os.StatFs;
import android.os.storage.StorageManager;

import androidx.appcompat.app.AppCompatActivity;

import com.racartech.library.rctandroid.file.RCTfile;
import com.racartech.library.rctandroid.math.RCTdataSizeConverter;
import com.racartech.library.rctandroid.notifications.RCTnotifications;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class RCTdiskInformation{




    public static long getDisk_FreeDiskSpace(String disk_path){
        if(disk_path != null){
            StatFs statFs = new StatFs(disk_path);
            return statFs.getAvailableBytes();
        }else{
            return 0;
        }
    }
    public static long getDisk_TotalDiskSpace(String disk_path){
        if(disk_path != null){
            StatFs statFs = new StatFs(disk_path);
            return statFs.getTotalBytes();
        }else{
            return 0;
        }
    }

    public static long getDisk_UsedDiskSpace(String disk_path){
        return getDisk_TotalDiskSpace(disk_path)-getDisk_FreeDiskSpace(disk_path);
    }

    public static double getDisk_UsedDiskSpace_Percentage(String disk_path){
        double used_cap = getDisk_UsedDiskSpace(disk_path);
        double total_cap = getDisk_TotalDiskSpace(disk_path);
        return (used_cap/total_cap)*100.0;
    }




    public static long getSDCARD_FreeDiskSpace(Context context){
        String sdcard_path = RCTfile.getExternalSdCardPath(context);
        if(sdcard_path != null){
            StatFs statFs = new StatFs(sdcard_path);
            return statFs.getAvailableBytes();
        }else{
            return 0;
        }
    }
    public static long getSDCARD_TotalDiskSpace(Context context){
        String sdcard_path = RCTfile.getExternalSdCardPath(context);
        if(sdcard_path != null){
            StatFs statFs = new StatFs(sdcard_path);
            return statFs.getTotalBytes();
        }else{
            return 0;
        }
    }
    public static long getSDCARD_UsedDiskSpace(Context context) {
        return getSDCARD_TotalDiskSpace(context)-getSDCARD_FreeDiskSpace(context);
    }

    public static double getSDCARD_UsedDiskSpacePercentage(Context context){
        double total_disk_space = getSDCARD_TotalDiskSpace(context);
        double used_disk_space = getSDCARD_UsedDiskSpace(context);
        return (used_disk_space/total_disk_space)*100;
    }


    /**
     * <h2>Description</h2>
     * Returns device internal storage free disk space in bytes
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @since 07-21-2022
     */
    public static long getInternalStorage_FreeDiskSpace() {
        StatFs statFs = new StatFs(RCTfile.getDir_ExternalStorageRoot());
        return statFs.getAvailableBytes();
    }

    /**
     * <h2>Description</h2>
     * Returns device internal storage total disk space in bytes
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @since 07-21-2022
     */
    public static long getInternalStorage_TotalDiskSpace() {
        StatFs statFs = new StatFs(RCTfile.getDir_ExternalStorageRoot());
        return statFs.getTotalBytes();
    }

    /**
     * <h2>Description</h2>
     * Returns device internal storage used disk space in bytes
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @since 07-21-2022
     */
    public static long getInternalStorage_UsedDiskSpace() {
        return getInternalStorage_TotalDiskSpace()-getInternalStorage_FreeDiskSpace();
    }

    public static double getInternalStorage_UsedDiskSpacePercentage(){
        double total_disk_space = getInternalStorage_TotalDiskSpace();
        double used_disk_space = getInternalStorage_UsedDiskSpace();
        return (used_disk_space/total_disk_space)*100;
    }

}
