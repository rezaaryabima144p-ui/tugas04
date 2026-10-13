package com.kuliah;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount(100.0);
    }

    @Test
    @DisplayName("Saldo awal default = 0")
    void saldoAwalDefault() {
        assertEquals(0.0, new BankAccount().getBalance(), 0.001);
    }

    @Test
    @DisplayName("Saldo awal negatif -> exception")
    void saldoAwalNegatif() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount(-1));
    }

    @Test
    @DisplayName("deposit menambah saldo")
    void deposit_valid() {
        account.deposit(50.0);
        assertEquals(150.0, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("deposit negatif -> exception, saldo tetap")
    void deposit_negatif() {
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-10));
        assertEquals(100.0, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("withdraw mengurangi saldo")
    void withdraw_valid() {
        account.withdraw(40.0);
        assertEquals(60.0, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("withdraw seluruh saldo -> saldo 0")
    void withdraw_semuaSaldo() {
        account.withdraw(100.0);
        assertEquals(0.0, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("withdraw negatif -> exception")
    void withdraw_negatif() {
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-5));
    }

    @Test
    @DisplayName("withdraw melebihi saldo -> exception, saldo tetap")
    void withdraw_melebihiSaldo() {
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(100.01));
        assertEquals(100.0, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("getBalance setelah deposit dan withdraw berurutan")
    void getBalance_berurutan() {
        account.deposit(200.0);
        account.withdraw(50.0);
        assertEquals(250.0, account.getBalance(), 0.001);
    }
}
