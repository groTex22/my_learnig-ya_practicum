package ru.praktikumService.scooter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HeaderFragment extends BasePage {
    //Кнопка "Заказать" в шапке
    private final By makeOrderButton = By.className("Button_Button__ra12g");
    //to_do: другие переменные пока не используются, сделаны на будущее
    //Кнопка "Статус заказа"
    private final By orderStatusButton = By.className("Header_Link__1TAG7");
    //Ввод номера заказа
    private final By inputOrderNumber = By.xpath(".//input[@class='Input_Input__1iN_Z Header_Input__xIoUq']");
    //Кнопка "Go" поиска по номеру заказа
    private final By searchOrder = By.className("Button_Button__ra12g Header_Button__28dPO");

    public HeaderFragment(WebDriver driver) {
        super(driver);
    }
    //клик по кнопке "Заказать" в шапке страницы
    public OrderPage clickMakeOrderButton(){
        driver.findElement(makeOrderButton).click();
        return new OrderPage(driver);
    }
    //to_do: то что ниже пока не используются, сделаны на будущее
    //Клик по кнопке "Статуса заказа"
    public void clickOrderStatusButton(){
        driver.findElement(orderStatusButton).click();
    }
    //Клик по кнопке поиска по номеру заказа
    public void clickSearchOrder(){
        driver.findElement(searchOrder).click();
    }
    //Ввод номера в строку поиска заказа
    public void inputOrderNumber(String orderNumber) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputOrderNumber)).sendKeys(orderNumber);
    }

}
