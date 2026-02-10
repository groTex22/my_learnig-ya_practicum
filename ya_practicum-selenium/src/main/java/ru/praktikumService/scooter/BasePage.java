package ru.praktikumService.scooter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    public static final String URL = "https://qa-scooter.praktikum-services.ru";
    //Кнопка заказать внизу страницы
    private final By makeOrderButton = By.xpath(".//Button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 3);
    }


    //Получить текст из faq после того как раскрыли кликом
    //Ожидание здесь не нужно
    public String getFaqQuestions(String faqNumber) {
         final String faqQuestions = ".//div[@id='accordion__heading-%s']";
         final String faqResponces = ".//div[@id='accordion__panel-%s']";

        driver.findElement(By.xpath(String.format(faqQuestions, faqNumber)))
                .click();
        return driver.findElement(By.xpath(String.format(faqResponces, faqNumber)))
                .getText();
    }

    //Кликнуть на кнопку "заказать"
    public OrderPage clickMakeOrderButton(){
        driver.findElement(makeOrderButton).click();
        return new OrderPage(driver);
    }
}