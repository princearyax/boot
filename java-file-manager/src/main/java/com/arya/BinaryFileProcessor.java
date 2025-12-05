package com.arya;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BinaryFileProcessor {
    public static void main(String[] args) {
        File inputFile = new File("i.jpg");
        File outputFile = new File("o.jpg");

        // A simple "Key" for encryption.
        // In binary: 10101010. We will XOR every byte with this.
        int key = 170;

        try (
            //Input Stream Chain
            FileInputStream fis = new FileInputStream(inputFile);
            BufferedInputStream bis = new BufferedInputStream(fis);

            //Output Stram Chain
            FileOutputStream fos = new FileOutputStream(outputFile);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
        ) {
            // 3. The Buffer (The Bucket)
            // We read 4KB (4096 bytes) at a time. This is standard efficient size.
            byte[] buffer = new byte[4096];

            int bytesRead;

            System.out.println("Encrypting binary things..");

            // 4. The Loop
            // bis.read(buffer) fills the array and returns the count of bytes read.
            // It returns -1 when EOF (End of File) is reached.
            while((bytesRead = bis.read(buffer)) != -1){
                //process specific bytes
                for(int i = 0; i < bytesRead; i++){
                    buffer[i] = (byte) (buffer[i]^key);
                }
                //write only what we read
                // bos.write(buffer) is WRONG. It would write the whole 4096 bytes 
                // even if we only read 50 bytes at the end.
                // We must use write(buffer, offset, length).
                bos.write(buffer, 0, bytesRead);
            }

            // Force any remaining bytes in the buffer to be written to disk
            bos.flush(); 

            System.out.println("Done! 'o.jpg' created.");
            System.out.println("Try opening it - it should be broken/invalid.");
            System.out.println("Run this code again with 'o.png' as input to decrypt it!");


        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }catch(IOException e){
            System.err.println("I/O Error: " + e.getMessage());
        }
    }
}
