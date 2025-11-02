package com.racartech.library.rctandroidx.app

import android.content.Context
import com.racartech.library.rctandroid.file.RCTdirectory
import com.racartech.library.rctandroidx.file.RCTdirectoryX
import com.racartech.library.rctandroidx.file.RCTfileX

object RCTappX{


    public fun initInternalAppFiles(context : Context, rootDataDirName : String){
        var targetDirectory  = RCTfileX.getDirIntAppFiles(appContext = context)+"/"+rootDataDirName
        if(!RCTdirectoryX.directoryExists(targetDirectory)){
            RCTdirectoryX.createDirectory(targetDirectory)
        }
    }



}