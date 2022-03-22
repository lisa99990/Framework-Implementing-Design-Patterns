package framework;

public class AccountDatabaseFactoryImplementation implements AccountDatabaseFactory{

    private volatile AccountDatabase accountDatabase;
    private volatile AccountService accountService;

    @Override
    public AccountService createAccountService(AccountDatabaseType accountDatabaseType) {
        if(accountService == null) {
            synchronized (AccountDatabaseFactoryImplementation.class) {
                if(accountService == null) {
                    accountService = new AccountServiceImplementation(createAccountDAO(accountDatabaseType));
                }
            }
        }
        return accountService;
    }

   

    private AccountDatabase createAccountDAO(AccountDatabaseType accountDAOType) {
        switch (accountDAOType) {
            case IN_MEMORY:
                accountDatabase =  createInMemoryDAO();
                break;
            case MOCK:
                accountDatabase = createMockDAO();
            default:
        }
        return accountDatabase;
    }
    private AccountDatabase createInMemoryDAO() {
        if(accountDatabase == null) {
            synchronized (AccountDatabaseFactoryImplementation.class) {
                if(accountDatabase == null) {
                    accountDatabase = new AccountDatabaseImplementation();
                }
            }
        }
        return  accountDatabase;
    }
    private AccountDatabase createMockDAO() {
        if(accountDatabase == null) {
            synchronized (AccountDatabaseFactoryImplementation.class) {
                if(accountDatabase == null) {
                    accountDatabase = new AccountDatabaseMock();
                }
            }
        }
        return accountDatabase;
    }
}
