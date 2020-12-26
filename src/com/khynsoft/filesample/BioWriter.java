package com.khynsoft.filesample;

import java.io.*;

public class BioWriter {
    private File f;

    public void createFile(String dirName){
        f = new File(dirName);
    }

    public void write(String name, int age) throws FileNotFoundException{
        if(!f.exists())
            throw new FileNotFoundException("Can't find the created file. Run createFile() first.");

        PrintWriter writer = new PrintWriter(f);
        writer.printf("Name: %s\nAge: %d", name, age);
        writer.flush();
        writer.close();
    }

    public File getFile() {
        return f;
    }
}
