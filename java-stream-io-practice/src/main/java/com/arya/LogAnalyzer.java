package com.arya;

// import java.io.BufferedReader;
// import java.io.BufferedWriter;
// import java.io.FileReader;
// import java.io.FileWriter;
// import java.io.IOException;

// //new things like java nio exits
// //files, channels, buffers, selector, path, paths...

// //wap to red file, count how many times java appears
// public class Practice {
//     public static void main(String[] args) {
//         try (
//             FileReader fr = new FileReader("large_log.txt");
//             BufferedReader bfr = new BufferedReader(fr);
//             BufferedWriter bwr = new BufferedWriter(new FileWriter("summary_report.txt"));
//         ) {
//             String line;
//             while ((line = bfr.readLine())!= null) {
//                 if(line.toLowerCase().contains("java".toLowerCase())){
//                     bwr.write(line);
//                     bwr.newLine();
//                     //code for writing how many times...
//                 }
//             }
//         } catch (IOException e) {
//             System.out.println("Some exceptions");
//         }
//     }
// }



import java.io.*;

public class LogAnalyzer {
    public static void main(String[] args) {
        
        File inputFile = new File("large_log.txt");
        File outputFile = new File("summary_report.txt");
        
        // Validation: Always check if input exists before opening streams
        if (!inputFile.exists()) {
            System.err.println("Input file not found!");
            return;
        }

        int matchCount = 0;

        try (
            BufferedReader bfr = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bwr = new BufferedWriter(new FileWriter(outputFile));
        ) {
            
            // Header for the report
            bwr.write("--- Log Summary Report ---");
            bwr.newLine();
            bwr.newLine(); // Empty line for spacing

            String line;
            while ((line = bfr.readLine()) != null) {
                // Check if line contains "java" (Case Insensitive)
                if (line.toLowerCase().contains("java")) {
                    bwr.write(line);
                    bwr.newLine(); // CRITICAL: Adds \n or \r\n depending on OS
                    matchCount++;
                }
            }

            // Footer with the count
            bwr.newLine();
            bwr.write("--------------------------");
            bwr.newLine();
            bwr.write("Total occurrences found: " + matchCount);

            System.out.println("Analysis Complete. Found " + matchCount + " matches.");

        } catch (IOException e) {
            System.err.println("I/O Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}