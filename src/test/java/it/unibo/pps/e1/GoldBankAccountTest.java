package it.unibo.pps.e1;

import org.junit.jupiter.api.Test;

public class GoldBankAccountTest extends AbstractBankAccountTest {
	@Override
	protected BankAccount createAccount(BankAccountFactory factory) {
		return factory.getGoldBankAccount(new CoreBankAccount());
	}

	@Test
	public void testCanWithdraw() {
		checkBalanceAfterWithdraw(200, 150, 50);
	}

	@Test
	public void testCanOverdraft() {
		checkBalanceAfterWithdraw(100, 300, -200);
	}

	@Test
	public void testCannotWithdrawMoreThanMaxOverdraft() {
		checkIllegalWithdraw(100, 700);
	}
}
