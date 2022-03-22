package bank;

import framework.*;

import java.time.LocalDate;
import java.util.List;

public abstract class BankAccount extends Account {
    public BankAccount(String accountNumber, Customer customer) {
        super(accountNumber, customer);
        //setInterestStrategy(new CheckingInterestStrategy());
        if (customer.getClass() == Company.class) {
            addObserver(new CompanyEmailNotification());
        } else if (customer.getClass() == Person.class) {
            addObserver(new PersonalEmailNotification());
        }

    }

    @Override
    public void deposit(Double amount) {
        balance += amount;
        String description = "deposited";
        Transaction transaction = new Transaction(amount, LocalDate.now(), description);
        addTransaction(transaction);
        notifyObserver(amount);

    }

    @Override
    public void withdraw(Double amount) {
        if ((balance - amount) < 0) {
            System.out.println("There is no enough fund in your account to withdraw...");
            notifyObserver(-amount);
        } else {
            balance -= amount;
            String description = "withdrawn";
            Transaction transaction = new Transaction(amount, LocalDate.now(), description);
            addTransaction(transaction);
            notifyObserver(-amount);
        }
    }

    @Override
    public void addInterest() {
        deposit(getInterestStrategy().getInterest(balance));
    }

    @Override
    public List<String> report(LocalDate from, LocalDate to) {
        return new BankReport(this, from, to).generateReport();
    }

}


