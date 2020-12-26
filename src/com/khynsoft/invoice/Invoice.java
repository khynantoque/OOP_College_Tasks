package com.khynsoft.invoice;

public class Invoice {
    private int itemNumber = 0, quantity = 0;
    private double price, totalCost = 0;
    private String name = "";

    public void displayLine() {
        System.out.printf(
                "Item Number: %d\n" +
                        "Item Name: %s\n" +
                        "Quantity: %d\n" +
                        "Price: P%,.2f\n" +
                        "Total Cost: P%,.2f\n",
                itemNumber, name, quantity, price, totalCost
        );
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        totalCost = price * quantity;
    }

    public void setPrice(double price) {
        this.price = price;
        totalCost = price * quantity;
    }

    public void setName(String name) {
        this.name = name;
    }
}
