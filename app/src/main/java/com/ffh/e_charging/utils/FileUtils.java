package com.ffh.e_charging.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @auther tuxy
 * @date 2015/4/13.
 */
public class FileUtils {
    /**
     * 处理文件 , 拷贝文件
     *
     * @param fromFile
     * @param toFile
     */
    public static boolean copyFile(File fromFile, File toFile) {

        if (!fromFile.exists() || !fromFile.isFile()) {
            return false;
        }

        if (!toFile.exists()) {
            try {
                toFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileOutputStream outputStream = null;
        FileInputStream inputStream = null;
        try {
            outputStream = new FileOutputStream(toFile);

            inputStream = new FileInputStream(fromFile);


            int len = 0;
            byte[] buffer = new byte[512];

            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }

            return true;

        } catch (Exception e) {

        } finally {

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return false;
    }
}
