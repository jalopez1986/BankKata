package com.feature;

import com.jlopez.bankkata.Account;
import com.jlopez.bankkata.Console;
import com.jlopez.bankkata.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PrintStatementFeature {
    @Mock
    Console console;

    private Account account;

    @Before
    public void setUp() throws Exception {
        TransactionRepository transactionRepository = new TransactionRepository();
        account = new Account(transactionRepository);
    }

    @Test
    public void print_statement_containing_all_transaction() {
        account.deposit(1000);
        account.withdraw(100);
        account.deposit(500);

        account.printStatement();

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).printLine("DATE | AMOUNT | BALANCE");
        inOrder.verify(console).printLine("10/04/2020 | 500.00 | 1400.00");
        inOrder.verify(console).printLine("02/04/2020 | -100.00 | 900.00");
        inOrder.verify(console).printLine("10/04/2020 | 1000.00 | 1000.00");
    }
}
