package it.unibo.pps.e1;

import java.util.function.IntUnaryOperator;

class BankAccountDecorator implements BankAccount {

    private final BankAccount base;
    private final int maxOverdraft;
    private final IntUnaryOperator fee;

	BankAccountDecorator(BankAccount base, int maxOverdraft, IntUnaryOperator fee) {
		this.base = base;
        this.maxOverdraft = maxOverdraft;
        this.fee = fee;
	}

    public int getBalance() {
        return this.base.getBalance();
    }

    public void deposit(int amount) {
        if (amount < 0) {
            throw new IllegalStateException("Cannot deposit negative amount");
        }
        this.base.deposit(amount);
    }

    public void withdraw(int amount) {
        int total = amount + fee.applyAsInt(amount);
        if (amount < 0 || total > getBalance() + this.maxOverdraft){
            throw new IllegalStateException("Withdrawal not allowed");
        }
        this.base.withdraw(total);
    }
}
