package com.khynsoft.vending_machine;

public class Dispenser {
    private int numOfItems;
    private final int cost;

    public Dispenser() {
        numOfItems = 50;
        cost = 50;
    }

    public Dispenser(int numOfItems, int cost) {
        if(numOfItems >= 0)
            this.numOfItems = numOfItems;
        else
            this.numOfItems = 50;

        if(cost >= 0)
            this.cost = cost;
        else
            this.cost = 50;
    }

    public int getProductCost() {
        return cost;
    }

    public int getCount() {
        return numOfItems;
    }

    public void makeSale() {
        numOfItems--;
    }
}
