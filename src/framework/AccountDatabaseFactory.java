package framework;

public interface AccountDatabaseFactory {
    AccountService createAccountService(AccountDatabaseType accountDatabaseType);
}
