package creditcard;

import bank.BankReport;
import framework.Account;
import framework.Customer;
import framework.Report;
import framework.Transaction;

import javax.imageio.plugins.tiff.GeoTIFFTagSet;
import java.time.LocalDate;
import java.util.List;

public abstract class CreditCardAccount extends Account {
    private LocalDate expirationDate;
    private Double limit = 0.0;
    private Double minimumPayment = 0.0;


    public CreditCardAccount(String accountNumber, Customer customer, LocalDate expirationDate) {
        super(accountNumber, customer);
        this.expirationDate = expirationDate;
    }

    @Override
    public void deposit(Double amount) {
        balance -= amount;
        String description = "deposited";
        Transaction transaction = new Transaction(amount, LocalDate.now(), description);
        addTransaction(transaction);
        notifyObserver(-amount);
    }

    @Override
    public void withdraw(Double amount) {

        if ((balance + amount) <= limit) {
            balance += amount;
            String description = "Charged";
            Transaction transaction = new Transaction(amount, LocalDate.now(), description);
            addTransaction(transaction);
            notifyObserver(amount);
        } else {
            System.out.println("You have passed the limit");
        }
    }

    @Override
    public void addInterest() {
        double interestAmount = getInterestStrategy().getInterest(balance);

        balance += interestAmount;
        Transaction transaction = new Transaction(interestAmount, LocalDate.now(), "interest added");
        addTransaction(transaction);
        notifyObserver(interestAmount);


    }

    @Override
    public List<String> report(LocalDate from, LocalDate to) {
        return new CreditCardReport(this, from, to).generateReport();
    }

    public void getMonthlyReport() {
        generateMonthlyBill();
        System.out.println("----------MONTHLY REPORT----------");
        System.out.println("Balance:" + this.getBalance());
        LocalDate now = LocalDate.now().minusDays(-1);
        for (String report : report(LocalDate.of(now.getYear(), now.getMonthValue() - 1, now.getDayOfMonth() - 1), now)) {
            System.out.println(report);
        }
    }

    public void setLimit(Double limit) {
        this.limit = limit;
    }

    public Double getLimit() {
        return limit;
    }

    public Double getMinimumPayment() {
        return minimumPayment;
    }

    public void setMinimumPayment(Double minimumPayment) {
        this.minimumPayment = minimumPayment;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void generateMonthlyBill() {
        balance = balance + (minimumPayment / 100) * (balance);
    }
}



