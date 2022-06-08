package Bank;

public class App 
{
    public static void main( String[] args )
    {
        BankAccount b1 = new BankAccount("Jeff Bezos");
        FixedDepositAccount fda1 = new FixedDepositAccount("Bill Gates", 100.10f);
        System.out.println(b1.getAccBal());
        System.out.println(fda1.getBalance());
        
        // change int and dur once
        fda1.setInterest(6);
        fda1.setDuration(3);
        System.out.println(fda1.getBalance());

        // change int and dur once
        // fda1.setInterest(10);
        // fda1.setDuration(2);

    }
}
