package com.feature;

import com.jlopez.bankkata.Console;
import com.jlopez.bankkata.StatementPrinter;
import com.jlopez.bankkata.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.*;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StatementPrinterShould {
    private static final List<Transaction> NO_TRANSACTIONS = Collections.emptyList();

    @Mock Console console;
    private StatementPrinter statementPrinter;

    @Before
    public void setUp() {
        statementPrinter = new StatementPrinter(console);
    }

    @Test
    public void always_print_header() {
        statementPrinter.print(NO_TRANSACTIONS);

        verify(console).printLine("DATE | AMOUNT | BALANCE");
    }

    @Test
    public void print_transactions_in_reverse_chronological_order() {
        List<Transaction> transactions = transactionsContaing(
                                            deposit("01/04/2020", 1000),
                                            withdrawal("02/04/2020", 100),
                                            deposit("10/04/2020", 500));

        statementPrinter.print(transactions);

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).printLine("DATE | AMOUNT | BALANCE");
        inOrder.verify(console).printLine("10/04/2020 | 500.00 | 1400.00");
        inOrder.verify(console).printLine("02/04/2020 | -100.00 | 900.00");
        inOrder.verify(console).printLine("01/04/2020 | 1000.00 | 1000.00");
    }

    private List<Transaction> transactionsContaing(Transaction... transactions) {
        return asList(transactions);
    }

    private Transaction withdrawal(String date, int amount) {
        return new Transaction(date, -amount);
    }

    private Transaction deposit(String date, int amount) {
        return new Transaction(date, amount);
    }
}