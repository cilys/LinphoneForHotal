package com.cilys.linphoneforhotal.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class ApkUtils {

    public static boolean install(String apkPath) {
        L.d(ApkUtils.class.getSimpleName(), "install apkPath = " + apkPath);
        boolean result = false;
        DataOutputStream dataOutputStream = null;
        BufferedReader errorStream = null;
        try {
            Process process = Runtime.getRuntime().exec("su");
            dataOutputStream = new DataOutputStream(process.getOutputStream());
            String command = "pm install -r " + apkPath + "\n";
            dataOutputStream.write(command.getBytes(Charset.forName("utf-8")));
            dataOutputStream.flush();
            dataOutputStream.writeBytes("exit\n");
            dataOutputStream.flush();
            process.waitFor();
            errorStream = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String msg = "";
            String line;
            while ((line = errorStream.readLine()) != null) {
                msg += line;
            }
            L.i(ApkUtils.class.getSimpleName(), "install msg = " + msg);
            if (!msg.contains("Failure")) {
                result = true;
            }
        } catch (Exception e) {
            L.printException(e);
        } finally {
            try {
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                if (errorStream != null) {
                    errorStream.close();
                }
            } catch (IOException e) {
                L.printException(e);
            }
        }
        return result;
    }

    public static void checkVersion (String url){

    }

    public static void downloadApk(String url) {

    }
}
