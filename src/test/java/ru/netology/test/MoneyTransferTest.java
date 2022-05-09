package ru.netology.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;
import ru.netology.page.DashboardPage;
import ru.netology.page.TransferPage;

import static com.codeborne.selenide.Selenide.open;

class MoneyTransferTest {

    public int sum = 500;
    public String numberCard1 = "5559000000000001";
    public String numberCard2 = "5559000000000002";

    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        int startBalance1 = dashboardPage.getFirstCardBalance();
        int startBalance2 = dashboardPage.getSecondCardBalance();
        var transferPage = dashboardPage.chooseCard();
        transferPage.transferMoneyFromCard2ToCard1(sum, numberCard2);

        var dashBoardPageFinish = new DashboardPage();
        int finishBalance1 = dashBoardPageFinish.getFirstCardBalance();
        int finishBalance2 = dashBoardPageFinish.getSecondCardBalance();

        assertEquals(startBalance2 - sum, finishBalance2);
        assertEquals(startBalance1 + sum, finishBalance1);
    }
}

