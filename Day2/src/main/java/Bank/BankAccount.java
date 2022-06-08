package Bank;

import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class BankAccount {

    // global class variables
    private String accHolderName;
    private String accNo = UUID.randomUUID()
            .toString()
            .substring(0, 8);
    private float accBal = 0f;
    private List<String> transactions = new LinkedList<>();
    private boolean closed = false;
    private Date dateCreated;
    private Date dateClosed;

    // constructors
    public BankAccount(String accHolderName) {
        this.accHolderName = accHolderName;
        this.accBal = 0;
    }

    public BankAccount(String accHolderName, float initBalAmt) {
        this.accHolderName = accHolderName;
        this.accBal = initBalAmt;
    }

    // getters and setters
    public String getAccHolderName() {
        return accHolderName;
    }

    public void setAccHolderName(String accHolderName) {
        this.accHolderName = accHolderName;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public void setAccBal(float accBal) {
        this.accBal = accBal;
    }

    public List<String> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<String> transactions) {
        this.transactions = transactions;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateClosed() {
        return dateClosed;
    }

    public void setDateClosed(Date dateClosed) {
        this.dateClosed = dateClosed;
    }

    // methods
    public void deposit(float depositAmt) {
        // validate amt
        if (depositAmt <= 0) {
            throw new IllegalArgumentException("Cannot deposit invalid deposit amount");
        }
        if (this.closed) {
            // check if acc closed
            throw new IllegalArgumentException("Cannot deposit to closed account");
        }

        // successful deposit
        this.accBal += depositAmt;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String now = dtf.format(LocalDateTime.now());
        String record = String.format("Deposit $%.2f at %s", depositAmt, now);
        transactions.add(record);
        System.out.printf("%s\n", record);
    }

    public float withdraw(float withdrawAmt) {
        // validate amt
        if (withdrawAmt <= 0) {
            throw new IllegalArgumentException("Cannot withdraw invalid withdraw amount");
        }

        if (this.closed) {
            // check if acc closed
            throw new IllegalArgumentException("Cannot withdraw from closed account");
        }

        // successful withdraw
        this.accBal -= withdrawAmt;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String now = dtf.format(LocalDateTime.now());
        String record = String.format("Withdrew $%.2f at %s", withdrawAmt, now);
        transactions.add(record);
        System.out.printf("%s\n", record);
        return withdrawAmt;
    }

    public void showPastNTransactions(int n) {
        int length = transactions.size();
        List<String> pastNTransactions = transactions.subList(length - n, length);
        for (String t : pastNTransactions) {
            System.out.println(t);
        }
    }

    public float getAccBal() {
        return this.accBal;
    }

    public static void main(String[] args) {
        BankAccount b1 = new BankAccount("Jeff Bezos");
        System.out.println(b1.accBal);

        // test deposit - invalid amount
        // b1.deposit(-100.12f);
        // test deposit - closed acc
        // b1.closed = true;
        // b1.deposit(100.10f);
        // b1.closed = false;
        // test deposit - success
        b1.deposit(100.23f);
        b1.withdraw(10.23f);
        b1.deposit(3.21f);
        b1.withdraw(0.12f);
        System.out.println(b1.transactions);
        b1.showPastNTransactions(2);
    }

}