package com.vb;

// Abstraction
// abstract class
public abstract class Transaction {
    // Encapsulation
    private double amount;
    private String status;

    public Transaction(double amount) {
        this.amount = amount;
        this.status = "PENDING";
    }

    // Getter/Setter
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    protected void setStatus(String status) {
        this.status = status;
    }

    // Abstraction: must implement
    public abstract void process();
}
