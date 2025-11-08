package main.services;

import main.models.Account;
import main.models.Transaction;
import main.repositories.IApplicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

//Some tests for practice
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
            savedAccounts.removeIf(account -> account.getAccountName().equals(accountName));
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
    void testDeleteAccount_success() throws Exception {
        Account account1 = accountService.createAccount("Test account1");
        UUID accountId1 = account1.getAccountID();

        Account account2 = accountService.createAccount("Test account2");
        UUID accountId2 = account2.getAccountID();

        assertEquals(2, ((DummyRepository) dummyRepository).savedAccounts.size());
        assertEquals("Test account1", accountService.getAccount(accountId1).getAccountName());
        assertEquals("Test account2", accountService.getAccount(accountId2).getAccountName());

        accountService.deleteAccount(accountId1);

        assertEquals(1, ((DummyRepository) dummyRepository).savedAccounts.size());

        assertThrows(Exception.class, () -> accountService.getAccount(accountId1));

        assertEquals("Test account2", accountService.getAccount(accountId2).getAccountName());
    }

    @Test
    void testAddTransaction_success() throws Exception {
        Account account = accountService.createAccount("Test Account");
        UUID accountId = account.getAccountID();

        assertEquals(1, ((DummyRepository) dummyRepository).savedAccounts.size());
        assertEquals("Test Account", accountService.getAccount(accountId).getAccountName());

        Transaction testTransaction = new Transaction("Salary", 22000, LocalDate.of(2025, 11, 8), true);

        accountService.addTransaction(accountId, testTransaction);

        List<Transaction> transactions = accountService.getAllTransactions(accountId);

        assertTrue(transactions.contains(testTransaction));
    }

    @Test
    void testAddTransaction_failure() throws Exception {
        Account account = accountService.createAccount("Test account");
        UUID accountId = account.getAccountID();
        UUID wrongAccountId = UUID.randomUUID();

        assertEquals(1, ((DummyRepository) dummyRepository).savedAccounts.size());
        assertEquals("Test account", accountService.getAccount(accountId).getAccountName());

        Transaction testTransaction = new Transaction("Swish", 500, LocalDate.of(2025, 11, 8), true);

        accountService.addTransaction(accountId, testTransaction);

        List<Transaction> transactions = accountService.getAllTransactions(accountId);

        assertTrue(transactions.contains(testTransaction));

        assertThrows(Exception.class, () -> accountService.getAllTransactions(wrongAccountId));
    }

    @Test
    void testRemoveTransaction_success() throws Exception {
        Account account = accountService.createAccount("Test account");
        UUID accountId = account.getAccountID();

        assertEquals(1, ((DummyRepository) dummyRepository).savedAccounts.size());
        assertEquals("Test account", accountService.getAccount(accountId).getAccountName());

        List<Transaction> transactionsBefore = accountService.getAllTransactions(accountId);
        int before = transactionsBefore.size();

        Transaction testTransaction = new Transaction("Salary", 22000, LocalDate.of(2025, 10, 25), true);
        accountService.addTransaction(accountId, testTransaction);

        List<Transaction> transactionsAfter = accountService.getAllTransactions(accountId);
        assertEquals(before + 1, transactionsAfter.size());

        UUID transactionId = transactionsAfter.get(transactionsAfter.size() - 1).getId();
        accountService.removeTransaction(accountId, transactionId);

        List<Transaction> result = accountService.getAllTransactions(accountId);
        assertEquals(before, result.size());
    }

    @Test
    void testRemoveTransaction_failure() throws Exception {
        Account account = accountService.createAccount("Test account");
        UUID accountId = account.getAccountID();

        assertEquals(1, ((DummyRepository) dummyRepository).savedAccounts.size());
        assertEquals("Test account", accountService.getAccount(accountId).getAccountName());

        UUID wrongTransactionId = UUID.randomUUID();

        List<Transaction> transactionsBefore = accountService.getAllTransactions(accountId);
        int listBefore = transactionsBefore.size();

        Transaction testTransaction = new Transaction("Salary", 22000, LocalDate.of(2025, 10, 25), true);
        accountService.addTransaction(accountId, testTransaction);

        List<Transaction> transactionsAfter = accountService.getAllTransactions(accountId);
        int listAfter = transactionsAfter.size();

        assertEquals(listBefore + 1, listAfter);

        assertThrows(Exception.class, () -> accountService.removeTransaction(accountId, wrongTransactionId));
    }

    @Test
    void testShowBalanceAfterTransaction_success() throws Exception {
        Account account = accountService.createAccount("Test account");
        UUID accountId = account.getAccountID();

        assertEquals(1, ((DummyRepository) dummyRepository).savedAccounts.size());
        assertEquals("Test account", accountService.getAccount(accountId).getAccountName());

        List<Transaction> transactions = accountService.getAllTransactions(accountId);
        int transactionsBefore = transactions.size();

        double previousBalance = accountService.getBalance(accountId);

        Transaction testTransaction = new Transaction("Test", 100, LocalDate.of(2025, 11, 8), true);
        accountService.addTransaction(accountId, testTransaction);

        List<Transaction> transactions2 = accountService.getAllTransactions(accountId);
        int transactionsAfter = transactions2.size();

        assertEquals(transactionsBefore + 1, transactionsAfter);

        double newBalance = accountService.getBalance(accountId);

        assertEquals(previousBalance + 100, newBalance);
    }
}