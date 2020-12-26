package com.khynsoft.vending_machine;

public class CashRegister {
    private int cashOnHand;

    public CashRegister() {
        cashOnHand = 500;
    }

    public CashRegister(int cashOnHand) {
        if (cashOnHand >= 0)
            this.cashOnHand = cashOnHand;
        else
            this.cashOnHand = 500;
    }

    public int currentBalance() {
        return cashOnHand;
    }

    public void acceptAmount(int amountIn) {
        cashOnHand += amountIn;
    }
}
