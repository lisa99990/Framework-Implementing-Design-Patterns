package framework;

import java.util.Collection;
import java.util.Map;

public interface AccountDatabase {
    void saveAccount(Account account);
    void updateAccount(Account account);
    Account loadAccount(String accountNumber);
    public Map<String, Account> getAccounts();
}
