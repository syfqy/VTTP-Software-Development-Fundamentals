package Bank;
public class FixedDepositAccount extends BankAccount {

    private float interest = 3;
    private int duration = 6;
    private int interestTimesChanged = 0;
    private int durationTimesChanged = 0;
    
    public float getInterest() {
        return interest;
    }

    public int getDuration() {
        return duration;
    }

    public void setInterest(float newInterest) {
        if (interestTimesChanged < 1) {
            this.interest = newInterest;
            this.interestTimesChanged += 1;
        } else {
            throw new IllegalArgumentException("Cannot change interest more than once");
        }
    }

    public void setDuration(int newDuration) {
        if (durationTimesChanged < 1) {
            this.duration = newDuration;
            this.durationTimesChanged += 1;
        } else {
            throw new IllegalArgumentException("Cannot change duration more than once");
        }
    }

    public FixedDepositAccount(String name, float balance) {
        super(name, balance);
    }

    public FixedDepositAccount(String name, float balance, float interest) {
        super(name, balance);
        this.interest = interest;
    }

    public FixedDepositAccount(String name, float balance, float interest, int duration) {
        super(name, balance);
        this.interest = interest;
        this.duration = duration;
    }


    @Override
    public void deposit(float depositAmt) {
        //no op
    }

    @Override
    public float withdraw(float withdrawAmt) {
        //no op
        return 0f;
    }

    public float getBalance() {
        return super.getAccBal() + this.interest;
    }

    public static void main(String[] args) {
        FixedDepositAccount fda1 = new FixedDepositAccount("Jeff Bezos", 100f);
        System.out.println(fda1);
    }
}