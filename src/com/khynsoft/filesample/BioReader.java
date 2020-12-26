package com.khynsoft.filesample;

import java.io.*;

public class BioReader {
    private String output = "";
    private boolean detailed;

    public BioReader(boolean detailed) {
        this.detailed = detailed;
    }

    public void readFile(File file) throws IOException {
        if (detailed) System.out.println("Reading file from: " + file.getAbsolutePath());
        if (!file.exists()) throw new FileNotFoundException("File not found!");

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line;
        while((line = reader.readLine()) != null)
            output += line + "\n";
        System.out.println("===============File Content===============");
        System.out.println(output);
        reader.close();
    }
}
