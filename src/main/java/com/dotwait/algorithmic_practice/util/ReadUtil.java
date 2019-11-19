package com.dotwait.algorithmic_practice.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadUtil {
    public static String read(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        StringBuffer sb = new StringBuffer(1024);
        byte[] buffer = new byte[1024];
        int ch;
        while ((ch = fis.read(buffer)) != -1){
            for (int i = 0; i < ch; i++) {
                sb.append((char) buffer[i]);
            }
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
}
