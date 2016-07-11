package com.android.mystic.util;

import com.android.mystic.log.MysticLog;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by janagaraj.veluchamy on 6/22/2016.
 */
public class Util {

    public static void IOCopy(String destPath, String srcPath) throws IOException {
        MysticLog.d("Source File :"+ srcPath + " Dest File : "+ destPath );
        InputStream inPut = new FileInputStream(srcPath);
        OutputStream output = new FileOutputStream(destPath);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inPut.read(buffer))>0){
            output.write(buffer, 0, length);
        }
        output.flush();
        output.close();
        inPut.close();
    }

}
