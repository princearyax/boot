package com.arya;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyByBufferedStream {
    public static void main(String[] args) {
        try (
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream("java-stream-io-practice/src/main/resources/input.png"));
            BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream("java-stream-io-practice/src/main/resources/output2.png"));
        ) {
            byte[] buffer = new byte[1024]; //1kb
            int bytesRead;

            while((bytesRead = bis.read(buffer)) != -1){
                //write exact amount
                bout.write(buffer, 0, bytesRead);
                // while((bytesRead = bis.read(buffer)) != -1){
                //     bout.write(buffer, 1, bytesRead);
                // }
            }
            bout.flush();
            System.out.println("Done fast copying by buffer");
        } catch (IOException e) {
            System.out.println("Exception...");
        }
    }
    
    public static void reader() {
        
        // Typical chain.. File -> FileReader -> BufferedReader
        try (BufferedReader reader = new BufferedReader(new FileReader("logs.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("Ologs.txt"))) {

            String line;
            while ((line = reader.readLine()) != null) {
                
                // Filter: Only write lines that contain "ERROR"
                if (line.contains("ERROR")) {
                    System.out.println("Found Error: " + line);
                    writer.write(line);
                    writer.newLine(); // Adds \n compatible with the OS (Windows/Linux)
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
