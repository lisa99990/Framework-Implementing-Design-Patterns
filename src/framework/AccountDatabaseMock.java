package framework;

import java.util.Collection;
import java.util.Map;

public class AccountDatabaseMock implements AccountDatabase{
    @Override
    public void saveAccount(Account account) {

    }

    @Override
    public void updateAccount(Account account) {

    }

    @Override
    public Account loadAccount(String accountNumber) {
        return null;
    }

    @Override
    public Map<String, Account> getAccounts()  {
        return null;
    }
}
