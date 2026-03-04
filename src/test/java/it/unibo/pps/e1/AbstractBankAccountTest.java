package it.unibo.pps.e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

abstract public class AbstractBankAccountTest {
    private BankAccount account;

    @BeforeEach
    void init() {
        this.account = createAccount(new BankAccountFactory());
    }

    protected abstract BankAccount createAccount(BankAccountFactory factory);

    protected void checkBalanceAfterWithdraw(int deposit, int withdraw, int expectedBalance) {
        account.deposit(deposit);
        account.withdraw(withdraw);
        assertEquals(expectedBalance, account.getBalance());
    }

    protected void checkIllegalWithdraw(int deposit, int withdraw) {
        account.deposit(deposit);
        assertThrows(IllegalStateException.class, () -> account.withdraw(withdraw));
    }

    @Test
    void testInitialBalance() {
        assertEquals(0, account.getBalance());
    }
}
