package com.khynsoft.eatnrun.model;

import java.util.ArrayList;

public class Bill {
    private String customerName;
    private double subtotal = 0, tax = 0, totalBill = 0;
    // Madame dinako maaply ang arraylist na Menu, pasayloa ko madam gi improve lang nako hehe
    ArrayList<MenuOrder> menuOrders = new ArrayList<>();

    public ArrayList<MenuOrder> getMenuOrders() {
        return menuOrders;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }

    public static class MenuOrder {
        private final String orderName;
        private final double price;
        private double total;
        private int quantity;

        public MenuOrder(String orderName, double price, int quantity, double total) {
            this.orderName = orderName;
            this.price = price;
            this.quantity = quantity;
            this.total = total;
        }

        public String getOrderName() {
            return orderName;
        }

        public double getPrice() {
            return price;
        }

        public double getTotal() {
            return total;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
            total = price * quantity;
        }
    }
}
