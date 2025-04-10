package com.racartech.library.rctandroid.time.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class GoogleTime {


    public static long getNetworkTime(){

        boolean return_boolean = false;
        AtomicBoolean finished_boolean = new AtomicBoolean(false);
        AtomicLong atomic_value = new AtomicLong(-1L);

        new Thread(new Runnable() {
            @Override
            public void run() {
                timeSystem(finished_boolean,atomic_value);
            }
        }).start();

        while(!return_boolean || atomic_value.get() == -1L){
            if(!finished_boolean.get()){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }catch (Exception ignored){}
            }else{
                return_boolean = true;
            }
        }
        return atomic_value.get();
    }



    private static void timeSystem(AtomicBoolean finished_boolean, AtomicLong atomic_value) {
        String ntpServer = "time.google.com";
        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setSoTimeout(5000);
            InetAddress address = InetAddress.getByName(ntpServer);

            byte[] buffer = new byte[48];
            buffer[0] = 0b00100011;  // NTP request header
            DatagramPacket request = new DatagramPacket(buffer, buffer.length, address, 123);

            long requestTime = System.nanoTime();
            socket.send(request);

            DatagramPacket response = new DatagramPacket(buffer, buffer.length);
            socket.receive(response);
            long responseTime = System.nanoTime();

            long seconds = ((buffer[40] & 0xFFL) << 24) |
                    ((buffer[41] & 0xFFL) << 16) |
                    ((buffer[42] & 0xFFL) << 8) |  // Fixed the emoji here, replaced it with correct shift value
                    (buffer[43] & 0xFFL);

            long fraction = ((buffer[44] & 0xFFL) << 24) |
                    ((buffer[45] & 0xFFL) << 16) |
                    ((buffer[46] & 0xFFL) << 8) |  // Fixed the emoji here, replaced it with correct shift value
                    (buffer[47] & 0xFFL);

            long transmitTime = ((seconds - 2208988800L) * 1000) + ((fraction * 1000) / 0x100000000L);

            long networkDelay = (responseTime - requestTime) / 2 / 1_000_000; // Convert ns to ms

            atomic_value.set((transmitTime + networkDelay));
            finished_boolean.set(true);
        } catch (Exception e) {
            e.printStackTrace();
            finished_boolean.set(true);
        }
    }

}
