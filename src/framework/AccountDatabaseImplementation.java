package framework;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AccountDatabaseImplementation implements AccountDatabase{

    Map<String,Account> accountlist=new HashMap<String,Account>();


    public void saveAccount(Account account) {
        accountlist.put(account.getAccountNumber(),account); // add the new
    }

    public void updateAccount(Account account) {
        Account accountexist = loadAccount(account.getAccountNumber());
        if (accountexist != null) {
            accountlist.remove(accountexist); // remove the old
            accountlist.put(account.getAccountNumber(),account); // add the new
        }

    }

    public Account loadAccount(String accountNumber) {
        return this.accountlist.get(accountNumber);
    }

    public Map<String, Account> getAccounts() {
        return accountlist;
    }
}
