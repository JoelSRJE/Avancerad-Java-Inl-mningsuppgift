package main.services;

import main.models.Account;
import main.models.Transaction;
import main.repositories.IApplicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    private IApplicationRepository dummyRepository;
    private AccountService accountService;

    // Enkel dummy-repo
    static class DummyRepository implements IApplicationRepository {
        List<Account> savedAccounts = new ArrayList<>();

        @Override
        public void saveAccounts(Account account) {
            savedAccounts.add(account);
        }

        @Override
        public void saveTransactions(String accountName, List<Transaction> transactions) {}

        @Override
        public List<Account> loadAccounts() {
            return new ArrayList<>(savedAccounts);
        }

        @Override
        public List<Transaction> loadTransactions(String accountName) {
            return new ArrayList<>();
        }

        @Override
        public void deleteAccount(String accountName) {

        }
    }

    @BeforeEach
    void setUp() {
        dummyRepository = new DummyRepository();
        accountService = new AccountService(dummyRepository);
    }

    @Test
    void testCreateAccount_shouldSaveAccount() {
        Account account = accountService.createAccount("Test Account");

        // Kontrollera att kontot sparades i repository
        assertEquals(1, ((DummyRepository) dummyRepository).savedAccounts.size());

        // Kontrollera att kontonamnet är korrekt
        assertEquals("Test Account", account.getAccountName());
    }

    @Test
    void testGetAccount_success() throws Exception {
        Account account = accountService.createAccount("Test Account");
        UUID accountId = account.getAccountID();

        Account foundAccount = accountService.getAccount(accountId);


        assertEquals(1, ((DummyRepository) dummyRepository).savedAccounts.size());
        assertEquals("Test Account", account.getAccountName());
        assertEquals(account.getAccountName(), foundAccount.getAccountName());
    }

    @Test
    void testGetAccount_failure() throws Exception {
        Account account = accountService.createAccount("Test Account");
        UUID accountId = UUID.randomUUID();

        assertEquals(1, ((DummyRepository) dummyRepository).savedAccounts.size());
        assertEquals("Test Account", account.getAccountName());
        assertThrows(Exception.class, () -> accountService.getAccount(accountId));
    }

    @Test
    void testAddTransaction_success() throws Exception {
        // Todo
    }
}