package com.khynsoft.shipped_order;

import java.util.Scanner;

public class UserOrder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Order order = new Order();
        Order shippedOrder = new ShippedOrder();

        System.out.print("[Normal Order]\n" +
                "<customer number> <customer_name> <quantity_ordered> <unit_price>\n" +
                "e.g. 1 BoSxy_M4P4gM4H41 12 45.12\n" +
                "Enter data: ");
        String[] normalOrder = sc.nextLine().split(" ");

        checkInput(sc, order, normalOrder);

        System.out.print("\n[Shipped Order]\n" +
                "<customer number> <customer_name> <quantity_ordered> <unit_price>\n" +
                "e.g. 1 BoSxy_M4P4gM4H41 12 45.12\n" +
                "Enter data: ");
        normalOrder = sc.nextLine().split(" ");

        checkInput(sc, shippedOrder, normalOrder);

        sc.close();
    }

    private static void checkInput(Scanner sc, Order order, String[] normalOrder) {
        while((isNull(normalOrder) || normalOrder.length != 4) ||
                ( normalOrder[0].matches("^(\\-\\d+)|(\\D*)$") ||
                        normalOrder[2].matches("^(\\d+\\.\\d+)|(\\D*)$") ||
                        normalOrder[3].matches("^\\D*$")
                )) {
            System.out.print("Invalid data, please try again...\nEnter data: ");
            normalOrder = sc.nextLine().split(" ");
        }

        order.setCustomerNumber(Integer.parseInt(normalOrder[0]));
        order.setCostumerName(normalOrder[1].replaceAll("\\_", " "));
        order.setQuantityOrdered(Integer.parseInt(normalOrder[2]));
        order.setUnitPrice(Double.parseDouble(normalOrder[3]));
        order.computePrice();
        order.displayValues();
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
