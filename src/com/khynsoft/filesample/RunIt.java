package com.khynsoft.filesample;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RunIt {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BioWriter bw = new BioWriter();
        BioReader br = new BioReader(true);

        try {
            String name = sc.nextLine();
            int age = sc.nextInt();

            bw.createFile("bio.txt");
            bw.write(name, age);

            br.readFile(bw.getFile());
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("The program is doing weird again :/");
        } catch (InputMismatchException e1) {
            System.err.println("Invalid input! Exiting the program :/");
        }
    }
}
