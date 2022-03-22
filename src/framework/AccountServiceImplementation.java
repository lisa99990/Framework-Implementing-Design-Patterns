package framework;

import bank.CompanyEmailNotification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AccountServiceImplementation implements AccountService {
    private AccountDatabase accountDatabase;

    public AccountServiceImplementation(AccountDatabase accountDatabase) {
        this.accountDatabase = accountDatabase;
    }

    @Override
    public void createAccount(Account account) {

        accountDatabase.saveAccount(account);
    }

    @Override
    public void deposit(String accountNumber, Double amount) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            account.deposit(amount);
            accountDatabase.updateAccount(account);
            //account.notifyObservers(amount);
        }
    }

    @Override
    public void deduct(String accountNumber, Double amount) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            try {
                account.withdraw(amount);
                accountDatabase.updateAccount(account);
            } catch (Exception e) {
                amount = null;
                e.printStackTrace();
            }
            //account.notifyObservers(amount);
        }

    }

    @Override
    public Map<String, Account> getAllAccounts() {
        return (Map<String, Account>) accountDatabase.getAccounts();
    }

    @Override
    public Account getAccount(String accountNumber) {
        return accountDatabase.loadAccount(accountNumber);
    }

    @Override
    public void addInterestToAnAccount(String accountNumber) {
        Account account = getAccount(accountNumber);
        try {
            account.addInterest();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void addInterestToAllAccount() {
        Map<String, Account> allAccounts = getAllAccounts();
        allAccounts.forEach((accountNumber, account) -> {
            try {
                account.addInterest();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

    }

    @Override
    public List<String> generateReport(String[] accountNumbers, LocalDate from, LocalDate to) {
        List<String> reports = new ArrayList<>();
        for (String accountNumber : accountNumbers) {
            Account account = getAccount(accountNumber);
            if (account != null) {
                reports.add(account.report(from, to).toString());
            }
        }
        return reports;
    }

    @Override
    public List<String> generateReport() {
        Set<String> keys = getAllAccounts().keySet();
        String[] accounts = keys.toArray(new String[0]);
        return generateReport(accounts, LocalDate.of(1900, 12, 12), LocalDate.of(2100, 12, 12));

    }
}
