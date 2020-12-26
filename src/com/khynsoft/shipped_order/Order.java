package com.khynsoft.shipped_order;

public class Order {
    private String costumerName;
    private int customerNumber;
    private int quantityOrdered;
    private double unitPrice;
    protected double totalPrice;

    public double computePrice() {
        totalPrice = quantityOrdered * unitPrice;
        return totalPrice;
    }

    public String getCostumerName() {
        return costumerName;
    }

    public void setCostumerName(String costumerName) {
        this.costumerName = costumerName;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void displayValues() {
        System.out.printf("\n====[DATA]====\n" +
                "Customer Number: %d\n" +
                "Customer Name: %s\n" +
                "Quantity Ordered: %d\n" +
                "Unit Price: P%,.2f\n" +
                "Total Price: P%,.2f\n",
                customerNumber, costumerName, quantityOrdered, unitPrice, totalPrice);
    }
}
