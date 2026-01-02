package com.arya;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyByByteStream {
    public static void main(String[] args) {
        try (
            FileInputStream fis = new FileInputStream("java-stream-io-practice/src/main/resources/input.png");
            FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir")+"/java-stream-io-practice/src/main/resources/output.png");
        ) {
            int byteRead;
            //read() return -1 when end of file is reached
            while((byteRead = fis.read()) != -1){
                fos.write(byteRead);
                // System.out.println(byteRead);
            }
        } catch (IOException e) {
            System.err.println("io excep");
        }
        System.out.println(System.getProperty("user.dir"));
    }
}
