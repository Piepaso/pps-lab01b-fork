package it.unibo.pps.e1;

class CoreBankAccount {

    private int balance = 0;

    public int getBalance() {
        return this.balance;
    }

    public void deposit(int amount) {
        this.balance = this.balance + amount;
    }

    public void withdraw(int amount) {
        if (!isWithdrawAllowed(amount)){
            throw new IllegalStateException();
        }
        this.balance = this.balance - amount;
    }

    private boolean isWithdrawAllowed(int amount) {
        return amount >= 0 && amount <= this.getBalance();
    }
}
