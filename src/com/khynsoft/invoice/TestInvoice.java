package com.khynsoft.invoice;

import java.util.Scanner;

public class TestInvoice {
    public static void main(String[] args) {
        Invoice[] invoices = new Invoice[3];

        Scanner sc = new Scanner(System.in);

        for (Invoice invoice : invoices) {
            invoice = new Invoice();
            System.out.println("Please enter to add item:\n<item number> <item_name> <quantity> <price>\n" +
                    "e.g. 1 Potato_chippy 12 45.12");
            String[] tokens = sc.nextLine().split(" ");

            while((isNull(tokens) || tokens.length != 4) ||
                    ( tokens[0].matches("^(\\-\\d+)|(\\D*)$") ||
                            tokens[2].matches("^(\\d+\\.\\d+)|(\\D*)$") ||
                            tokens[3].matches("^\\D*$")
                    )) {

                    System.out.println("Input is invalid, try again: ");
                    tokens = sc.nextLine().split(" ");
            }

            invoice.setItemNumber(Integer.parseInt(tokens[0]));
            invoice.setName(tokens[1].replaceAll("\\_", " "));
            invoice.setQuantity(Integer.parseInt(tokens[2]));
            invoice.setPrice(Double.parseDouble(tokens[3]));
            invoice.displayLine();
            System.out.println();
        }

        System.out.println("Thank you for using the program :D");
        sc.close();
    }

    private static boolean isNull(String[] input) {
        for (String s : input) {
            if(s == null)
                return true;
            else if(s.isEmpty())
                return true;
        }
        return false;
    }
}
