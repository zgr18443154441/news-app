package com.shxt.news.utils;

import android.util.Log;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by 张国荣 on 2017/2/9.
 */

public class IpAddressTool {
    public static String getIpAddress(){
        String ip = null;
        try {
            for(InetAddress e : InetAddress.getAllByName("DESKTOP-MEBFCL0")){
                String[] inet = e.toString().split("/");
                if(inet[1].length()<16){
                    ip = inet[1];
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        Log.v("ip",ip==null?"null":ip);
        return ip;
    }
}
