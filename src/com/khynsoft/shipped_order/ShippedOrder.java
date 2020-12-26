package com.khynsoft.shipped_order;

public class ShippedOrder extends Order{
    @Override
    public double computePrice() {
        totalPrice = super.computePrice() + 450;
        return totalPrice;
    }
}
