package framework;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Account extends Subject{
    private String accountNumber;
    private InterestStrategy interestStrategy;
    private Customer customer;
    protected Double balance = 0.0;

    private List<Transaction> transactions;

    public Account(String accountNumber, Customer customer) {
        this.customer = customer;
        this.accountNumber = accountNumber;
        customer.addAccount(this);
        transactions = new ArrayList<>();
    }


    protected void setInterestStrategy(InterestStrategy interestStrategy) {
        this.interestStrategy = interestStrategy;
    }


    //Strategy pattern
    public double getInterestRate(){
        return interestStrategy.getInterestRate();
    }


    public abstract void addInterest();

    public abstract void deposit(Double amount);

    public abstract void withdraw(Double amount);

    public String getAccountNumber() {
        return accountNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Double getBalance() {
        return balance;
    }

    public InterestStrategy getInterestStrategy() {
        return interestStrategy;
    }

    protected void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    public abstract List<String> report(LocalDate from, LocalDate to);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        return accountNumber.equals(account.accountNumber);

    }

    public void addNotificationMethod(Notification notification) {
        this.addObserver(notification);
    }

}


