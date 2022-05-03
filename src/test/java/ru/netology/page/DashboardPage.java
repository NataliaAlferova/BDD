package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Value;
import lombok.val;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public TransferPage chooseCard() {
        buttons.first().click();
        return new TransferPage();
    }


    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    private ElementsCollection buttons = $$("[class='button__text']");

    public int getFirstCardBalance() {
        val text = cards.first().text();
        return extractBalance(text);
    }

    public int getSecondCardBalance() {
        val text = cards.last().text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    @Value
    public class Card {
        private String number;
    }

    public Card getcard1() {
        return new Card("5559 0000 0000 0001");
    }

    public Card getcard2() {
        return new Card("5559 0000 0000 0002");
    }
}