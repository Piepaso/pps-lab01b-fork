package it.unibo.pps.e1;

import org.junit.jupiter.api.Test;

class BronzeBankAccountTest extends AbstractBankAccountTest {
	@Override
	protected BankAccount createAccount(BankAccountFactory f) {
		return f.getBronzeBankAccount(new CoreBankAccount());
	}

	@Test
	void testWithdrawWithoutFee() {
		checkBalanceAfterWithdraw(100, 50, 50);
	}

	@Test
	void testWithdrawWithFee() {
		checkBalanceAfterWithdraw(500, 100, 399);
	}

	@Test
	void testCannotWithdrawMoreThanBalance() {
		checkIllegalWithdraw(100, 100);
	}
}