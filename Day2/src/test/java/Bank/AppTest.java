package Bank;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Bank.BankAccount;
import Bank.FixedDepositAccount;

/**
 * Unit test for simple App.
 */
public class AppTest {
    
    @Test
    public void testBankAcc() {
        BankAccount b1 = new BankAccount("Jeff Bezos");
        b1.deposit(100);
        b1.withdraw(60);
        float b1Bal = b1.getAccBal();
        assertEquals(40, b1Bal, .1);
    }

    @Test
    public void testInvalidFdaChangeInterest() {
        try {
            FixedDepositAccount fda1 = new FixedDepositAccount("Jeff Bezos", 100.22f);
            fda1.setInterest(100);
            fda1.setInterest(20);

        } catch (IllegalArgumentException e) {
            assertEquals("Cannot change interest more than once", e.getMessage());
        }
    }

    @Test
    public void testInvalidFdaChangeDuration() {
        try {
            FixedDepositAccount fda1 = new FixedDepositAccount("Jeff Bezos", 100.22f);
            fda1.setDuration(100);
            fda1.setDuration(20);

        } catch (IllegalArgumentException e) {
            assertEquals("Cannot change duration more than once", e.getMessage());
        }
    }

    @Test
    public void testValidFdaChangeInterest() {
        FixedDepositAccount fda1 = new FixedDepositAccount("Jeff Bezos", 100.22f);
        fda1.setInterest(10.1f);
        assertEquals(10.1f, fda1.getInterest(), 0.01);
    }
    
    @Test
    public void testValidFdaChangeDuration() {
        FixedDepositAccount fda1 = new FixedDepositAccount("Jeff Bezos", 100.22f);
        fda1.setDuration(100);
        assertEquals(100, fda1.getDuration());
    }
}
