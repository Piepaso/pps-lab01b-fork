package it.unibo.pps.e1;

public class BankAccount {

    private final int fee;

    public BankAccount(int fee) {
        this.fee = fee;
    }

    private CoreBankAccount base = new CoreBankAccount();

    public int getBalance() {
        return base.getBalance();
    }

    public void deposit(int amount) {
        base.deposit(amount);
    }

    public void withdraw(int amount) {
        base.withdraw(amount + fee);
    }

}
