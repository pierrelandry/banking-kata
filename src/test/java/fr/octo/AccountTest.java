package fr.octo;

import javafx.scene.media.AudioClip;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class AccountTest {

    @Mock private DateProvider dateProvider;

    @InjectMocks private Account account;

    @Before public void setUp () {
        Mockito.when(dateProvider.getNow()).thenReturn(LocalDate.of(2018, 4, 1));

        account = new Account(dateProvider);
    }

    @Test
    public void balanceShouldBeZeroAtTheInitialization() throws Exception {
        // Given

        // When
        int balanceResult = account.getBalance();

        //Then
        assertThat(balanceResult).isEqualTo(0);
    }

    @Test
    public void balanceShouldBeEqualTo50AfterAFirstDepositOf100() throws Exception {
        // Given

        // When
        account.deposit(50);
        int balanceResult = account.getBalance();

        //Then
        assertThat(balanceResult).isEqualTo(50);

    }

    @Test
    public void balanceShouldEqualTo300AfterThreeSuccessiveDepositOf50() throws Exception {
        // Given

        // When
        account.deposit(100);
        account.deposit(100);
        account.deposit(100);

        //Then
        assertThat(account.getBalance()).isEqualTo(300);
    }

    @Test(expected = Exception.class)
    public void withdrawShouldThrownAnExceptionWhenNotEnoughOfMoney() throws Exception {
        // Given

        // When
        account.deposit(40);
        account.withdraw(50);

    }

    @Test
    public void withdrawShouldUpdateBalanceByRemoving40() throws Exception {
        // Given

        // When
        account.deposit(100);
        account.withdraw(40);

        //Then
        assertThat(account.getBalance()).isEqualTo(60);
    }

    @Test
    public void balanceShouldEqualToZeroAfterThreeSuccessiveWithdrawsOf50() throws Exception {
        // Given
        account.deposit(150);

        // When
        account.withdraw(50);
        account.withdraw(50);
        account.withdraw(50);


        //Then
        assertThat(account.getBalance()).isEqualTo(0);
    }

    @Test
    public void printStatementShouldJustHaveHeaderAtTheBeginning() throws Exception {
        // Given

        // When

        //Then
        assertThat(account.printStatement()).isEqualTo("\nDate         Amount   Balance\n");
    }

    @Test
    public void printStatementShouldDisplayTheFirstDepositOf100() throws Exception {
        // Given

        // When
        account.deposit(100);

        //Then
        assertThat(account.printStatement()).isEqualTo("\nDate         Amount   Balance\n" +
                "01.04.2018     +100       100\n");

    }

    @Test
    public void printStatementShouldDisplayTheTwoDepositsTransactions() throws Exception {
        // Given

        // When
        account.deposit(100);
        account.deposit(50);

        //Then
        assertThat(account.printStatement()).isEqualTo("\nDate         Amount   Balance\n" +
                "01.04.2018     +100       100\n" +
                "01.04.2018      +50       150\n");

    }

    @Test
    public void printStatementShouldDisplayTheDepositTransactionsWithFourNumbers() throws Exception {
        // Given

        // When
        account.deposit(1000);

        //Then
        assertThat(account.printStatement()).isEqualTo("\nDate         Amount   Balance\n" +
                "01.04.2018    +1000      1000\n");

    }

    @Test
    public void printStatementShouldDisplayTAllTransactions() throws Exception {
        // Given

        // When
        account.deposit(900);
        account.withdraw(100);

        //Then
        assertThat(account.printStatement()).isEqualTo("\nDate         Amount   Balance\n" +
                "01.04.2018     +900       900\n" +
                "01.04.2018     -100       800\n");

    }

    @Test
    public void displaySomeTransactions() throws Exception {
        // Given

        // When
        account.deposit(900);
        account.withdraw(100);
        account.withdraw(100);
        account.deposit(50);
        account.deposit(1000);
        account.withdraw(100);
        account.withdraw(1650);
        account.printStatement();
        String printResult = account.printStatement();

        System.out.println(printResult);

        //Then
        assertThat(printResult).isEqualTo("\nDate         Amount   Balance\n" +
                "01.04.2018     +900       900\n" +
                "01.04.2018     -100       800\n" +
                "01.04.2018     -100       700\n" +
                "01.04.2018      +50       750\n" +
                "01.04.2018    +1000      1750\n" +
                "01.04.2018     -100      1650\n" +
                "01.04.2018    -1650         0\n");

    }

    @Test
    public void formatDateShouldDisplayTheCorrectFormat() throws Exception {
        // Given
        LocalDate nonFormattedDate = LocalDate.of(2018, 4, 10);

        // When
        String formattedDate = account.formatDate(nonFormattedDate);

        //Then
        assertThat(formattedDate).isEqualTo("10.04.2018");

    }
}
