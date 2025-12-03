package com.arya;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileProcessor 
{
    public static void main( String[] args )
    {
        //define input and output file path
        //in boot these will come from application.prop
        //here cwd is C:/Arya/code/gitclones/Spring/boot/java-file-manager/
        File inputFile = new File("users.csv");
        File outputFile = new File("users_clean.csv");

        //we'll use try with resources
        //java will automatically close the pipes even if the code crashes
        //this prevents resource leaks, file stays locked
        try (
            //reading pipe/ source
            FileReader fr = new FileReader(inputFile);
            BufferedReader reader = new BufferedReader(fr);

            //writing pipe/ output
            FileWriter fw = new FileWriter(outputFile);
            BufferedWriter writer = new BufferedWriter(fw);
        ) {
            String line;
            boolean isHeader = true; //let knowif the header in i/p csv

            System.out.println("Processing file..........");

            while ((line = reader.readLine()) != null) {
                //keep the header as it was
                if(isHeader){
                    writer.write(line);
                    writer.newLine();
                    isHeader = false;
                    continue;
                }

                String processedLine = sanitizeLine(line);

                writer.write(processedLine);
                writer.newLine();
            }
            System.out.println("Success..");
        } catch (FileNotFoundException e) {
            System.err.print("Couldn't find the file in defined path");
        } catch (IOException e){
            System.err.print("Error:"+ e.getMessage());
        }
    }

    /**
     * Logic to modify the raw CSV line.
     * Input:  "1,john doe,john.doe@gmail.com"
     * Output: "1,JOHN DOE,john.doe@***"
     */
    private static String sanitizeLine(String line){
        String[] parts = line.split(",");

        if(parts.length < 3) return line;

        String id = parts[0];
        String name = parts[1];
        String email = parts[2];

        String cleanName = name.toUpperCase();

        //mask email domain
        int atIdx = email.indexOf("@");
        String cleanEmail = (atIdx != -1)
            ? email.substring(0, atIdx) + "@***"
            : email;
        
        //reassemble
        return id + ", " +cleanName +", " +cleanEmail;
    }
}
