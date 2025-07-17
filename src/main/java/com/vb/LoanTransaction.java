package com.vb;

// Inheritance + Polymorphism
// subclass
public class LoanTransaction extends Transaction {
    public LoanTransaction(double amount) {
        super(amount);
    }

    @Override
    public void process() {
        System.out.println("[Loan] Approving loan of $" + getAmount());
        setStatus("APPROVED");
    }
}
