package com.pjqdyd.utils;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**   
 * @Description:  [生成唯一id的工具类]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@Slf4j
public class UniqueId {

    //生成唯一id的方法, id有大小顺序
    public static String  getNewId(String flag) {
        String ipAddress = "";
        try {
            //获取服务器IP地址
            ipAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            log.error("获取唯一Id错误 getNewId={}", e.getMessage());
        }
        //获取UUID
        String uuid = ipAddress + "$" + UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        //生成后缀
        long suffix = Math.abs(uuid.hashCode() % 100000000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        String time = sdf.format(new Date(System.currentTimeMillis()));
        //生成前缀
        long prefix = Long.parseLong(time) * 100000000;
        String newId = flag + String.valueOf(prefix + suffix);
        return newId;
    }

    //生成唯一id的方法, id是随机的顺序
    public static String  getNewIdRandom(String flag) {
        String ipAddress = "";
        try {
            //获取服务器IP地址
            ipAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            log.error("获取唯一Id错误 getNewId={}", e.getMessage());
        }
        //获取UUID
        String uuid = ipAddress + "$" + UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        //生成后缀
        long suffix = Math.abs(uuid.hashCode() % 100000000);

        //生成前缀
        int prefix = (int) (Math.random() * 100000000);

        String newId = flag + String.valueOf(prefix + suffix);
        return newId;
    }
}
