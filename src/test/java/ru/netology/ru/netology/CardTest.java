package ru.netology.ru.netology;

import com.codeborne.selenide.Condition;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardTest {
    private CardInfo user = Generator.generatedCard("ru");

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void checkForValidValues() {
        String planDate = Generator.generateDate(3);
        $("[data-test-id='city'] input").val(user.getCity());
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(planDate);
        $("[data-test-id='name'] .input__control").val(user.getName());
        $("[data-test-id='phone'] .input__control").val(user.getPhone());
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(withText("Успешно!")).shouldBe(visible);
        $("[data-test-id='success-notification'] .notification__content").shouldHave(text("Встреча успешно запланирована на " + planDate));

    }
    @Test
    void checksTheRecordConfirmationNotificationForANewDate() {
        String planningDate = Generator.generateDate(3);
        String planningDateLast = Generator.generateDate(5);


        $("[data-test-id='city'] input").val(user.getCity());
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(planningDate);
        $("[data-test-id='name'] .input__control").val(user.getName());
        $("[data-test-id='phone'] .input__control").val(user.getPhone());
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='success-notification'] .notification__content").shouldBe(text("Встреча успешно запланирована на " + planningDate), Duration.ofSeconds(1));
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(planningDateLast);
        $(".button").click();
        $("[data-test-id='replan-notification'] .notification__content").shouldHave(Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $("[data-test-id='replan-notification'] .button__text").click();
        $("[data-test-id='success-notification'] .notification__content").shouldBe(text("Встреча успешно запланирована на " + planningDateLast), Duration.ofSeconds(2));

    }
}
