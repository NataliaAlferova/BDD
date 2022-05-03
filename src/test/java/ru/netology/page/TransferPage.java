package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class TransferPage {

    private SelenideElement amount = $("[data-test-id='amount']");

    public TransferPage() {
        amount.shouldBe(visible);
    }

    public DashboardPage transferMoney (int sum) {
        $("[data-test-id='amount'] input").setValue(String.valueOf(sum));
        var dashboardPage = new DashboardPage();
        $("[data-test-id='from'] input").setValue(dashboardPage.getcard2().getNumber());
        $("[class='button__text']").click();

        return new DashboardPage();
    }
}
