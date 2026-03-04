package it.unibo.pps.e1;

public class BankAccountFactory {

	public BankAccount getBronzeBankAccount(BankAccount base) {
		return new BankAccountDecorator(base, 0, amount -> amount < 100 ? 0 : 1);
	}

	public BankAccount getSilverBankAccount(BankAccount base) {
		return new BankAccountDecorator(base, 0, amount -> 1);
	}

	public BankAccount getGoldBankAccount(BankAccount base) {
		return new BankAccountDecorator(base, 500, amount -> 0);
	}
}
