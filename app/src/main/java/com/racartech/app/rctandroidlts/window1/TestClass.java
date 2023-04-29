package com.racartech.app.rctandroidlts.window1;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class TestClass {
    public static void printSharedFolders() {
        System.out.println("A");
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            localhost.getHostName();
            byte[] ip = localhost.getAddress();
            System.out.println(localhost.getHostName());

            List<String> sharedFolders = new ArrayList<>();
            System.out.println("Shared Folder Size : ".concat(String.valueOf(sharedFolders.size())));
            for (int i = 0; i <= 254; i++) {
                ip[3] = (byte) i;
                InetAddress address = InetAddress.getByAddress(ip);

                if (address.isReachable(1000)) {
                    String hostname = address.getHostName();
                    if (hostname.startsWith("DESKTOP") || hostname.startsWith("LAPTOP")) { // Replace with your own prefix
                        sharedFolders.add("\\\\" + hostname + "\\");
                    }
                }
            }
            System.out.println("C");

            if (sharedFolders.isEmpty()) {
                System.out.println("No shared folders found on the network.");
            } else {
                System.out.println("Shared folders on the network:");
                for (String folder : sharedFolders) {
                    System.out.println(folder);
                }
            }
            System.out.println("D");

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
