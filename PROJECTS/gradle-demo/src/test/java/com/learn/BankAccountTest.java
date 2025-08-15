package com.learn;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class BankAccountTest {

    static BankAccount sharedAccount;
    BankAccount account;

    @BeforeAll
    static void initAll() {
        System.out.println("Runs once before all tests");
        sharedAccount = new BankAccount("SharedUser", 1000);
    }

    @BeforeEach
    void init() {
        System.out.println("Runs before each test");
        account = new BankAccount("John Doe", 500);
    }

    @Test
    @DisplayName("Test deposit increases balance")
    void testDeposit() {
        account.deposit(200);
        assertEquals(700, account.getBalance(), "Balance should increase after deposit");
    }

    @Test
    @DisplayName("Test withdraw decreases balance")
    void testWithdraw() {
        account.withdraw(100);
        assertEquals(400, account.getBalance());
        assertNotEquals(500, account.getBalance());
    }

    @Test
    @DisplayName("Test insufficient funds throws exception")
    void testWithdrawInsufficientFunds() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(1000);
        });
        assertEquals("Insufficient funds", ex.getMessage());
    }

    @Test
    @DisplayName("Test deposit with negative amount throws exception")
    void testNegativeDeposit() {
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-50));
    }

    @Test
    @DisplayName("Test account activation status")
    void testAccountActiveStatus() {
        assertTrue(account.isActive(), "Account should be active initially");
        account.deactivate();
        assertFalse(account.isActive(), "Account should be inactive after deactivation");
    }

    @Test
    @DisplayName("Test account owner is set correctly")
    void testOwnerName() {
        assertNotNull(account.getOwner());
        assertEquals("John Doe", account.getOwner());
    }

    @Test
    @Disabled("Pending implementation for transfer feature")
    void testTransfer() {
        fail("Transfer feature not yet implemented");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Runs after each test");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("Runs once after all tests");
    }
}
