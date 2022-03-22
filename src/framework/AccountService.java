package framework;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AccountService extends Report{
        void createAccount(Account account);
        void deposit(String accountNumber, Double amount);
        void deduct(String accountNumber, Double amount);
        Map<String, Account> getAllAccounts();
        Account getAccount(String accountNumber);
        void addInterestToAnAccount(String accountNumber);
        void addInterestToAllAccount();
        List<String> generateReport(String[] accountNumbers, LocalDate from, LocalDate to);
}
