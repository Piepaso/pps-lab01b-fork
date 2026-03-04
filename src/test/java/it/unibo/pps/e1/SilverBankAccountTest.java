package it.unibo.pps.e1;

import org.junit.jupiter.api.Test;

class SilverBankAccountTest extends AbstractBankAccountTest {
	@Override
	protected BankAccount createAccount(BankAccountFactory f) {
		return f.getSilverBankAccount(new CoreBankAccount());
	}

	@Test
	void testWithdraw() {
		checkBalanceAfterWithdraw(100, 50, 49);
	}

	@Test
	void testCannotWithdrawMoreThanBalance() {
		checkIllegalWithdraw(100, 100);
	}
}
