package com.example.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
public class TransactionLogger {

    public static void logActualTransactionActive() {
        final var currentTransactionName = TransactionSynchronizationManager.getCurrentTransactionName();
        final var actualTransactionActive = TransactionSynchronizationManager.isActualTransactionActive();
        Integer isolationLevel = TransactionSynchronizationManager.getCurrentTransactionIsolationLevel();
        final var emoji = actualTransactionActive ? "✅" : "❌";
        log.info("\n{} is Actual Transaction Active : {} {} {}", currentTransactionName, emoji, actualTransactionActive, isolationLevel);
    }
}
