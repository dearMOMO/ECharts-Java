package com.momo.phantomJs;

import com.google.common.collect.ImmutableMap;

import java.io.*;
import java.util.Map;

/**
 * @Author: Jie Li
 * @Version: 1.0
 * @Description: 初始化文件
 * <p>Copyright: Copyright (c) 2023</p >
 * @Date: 2023年11月01 23:20
 **/

public class InitFiles {

    public static Map<String, String> filePath = new ImmutableMap.Builder<String, String>()
            .put("win", "D:\\Program Files\\MOMO_phantomJs\\")
            .put("mac", "")
            .put("linux", "/home/momo/MOMO_phantomJs/")
            .build();

    public static void init() throws IOException {

        if (SystemUtils.isWindows()) {
            //创建文件夹
            File file = new File(filePath.get("win"));
            if (!file.isDirectory()) {
                //递归生成文件夹
                file.mkdirs();
            }

            // 通过ClassLoader读取resources下的文件
            InputStream inputStream = InitFiles.class.getClassLoader().getResourceAsStream("phantomjs.exe");
            writeFile(inputStream, filePath.get("win") + "phantomjs.exe");

        }
        if (SystemUtils.isLinux()) {
            //创建文件夹
            File file = new File(filePath.get("linux"));
            if (!file.isDirectory()) {
                //递归生成文件夹
                file.mkdirs();
            }

            // 通过ClassLoader读取resources下的文件
            InputStream inputStream = InitFiles.class.getClassLoader().getResourceAsStream("phantomjs");
            writeFile(inputStream, filePath.get("linux") + "phantomjs");

        }

    }

    private static void writeFile(InputStream inputStream, String path) throws IOException {
        File file = new File(path);
        boolean exists = file.exists();
        if (!exists) {
            // 创建输出流
            FileOutputStream outputStream = new FileOutputStream(path);

            // 读取源文件内容并写入目标文件
            int data;
            while ((data = inputStream.read()) != -1) {
                outputStream.write(data);
            }

            // 关闭流
            inputStream.close();
            outputStream.close();

            System.out.println("文件写入成功！");
        }


    }

}
