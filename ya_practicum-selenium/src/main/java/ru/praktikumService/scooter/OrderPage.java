package ru.praktikumService.scooter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.Keys;

public class OrderPage extends BasePage {
  //  public static final String URL = "https://qa-scooter.praktikum-services.ru/order";

    public OrderPage(WebDriver driver) {
        super(driver);
    }

    //ниже локаторы для формы заполнения
    private final By nameLocator = By.xpath(".//input[@placeholder = '* Имя']");
    private final By familyLocator = By.xpath(".//input[@placeholder = '* Фамилия']");
    private final By addressLocator = By.xpath(".//input[@placeholder = '* Адрес: куда привезти заказ']");
    private final By metroStationLocator = By.xpath(".//input[@placeholder = '* Станция метро']");
    private final By phoneNumberLocator = By.xpath(".//input[@placeholder = '* Телефон: на него позвонит курьер']");
    private final By dateOrderLocator = By.xpath(".//input[@placeholder = '* Когда привезти самокат']");
   // private final By periodRentLocator = By.xpath(".//div[@class = 'Dropdown-placeholder']");
    private final By periodRentLocator = By.xpath(".//span[@class = 'Dropdown-arrow']");
    private final By sutki = By.xpath(".//div[@class='Dropdown-option'][1]");
    //Кнопки "далее", "Заказать", "Да" - подтверждение заказа
    private final By nextButton = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Далее']");
    private final By orderButton = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Заказать']");
    private final By confirmationButton = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Да']");
    private final By orderComleted = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and text()='Заказ оформлен']");

    //Метод для заполнения имени
    public OrderPage sendName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameLocator)).sendKeys(name);
        return this;
    }
    //Метод для заполнения фамилии
    public OrderPage sendFamily(String family) {
        driver.findElement(familyLocator).sendKeys(family);
        return this;
    }
    //Метод для заполнения адреса
    public OrderPage sendAddress(String address) {
        driver.findElement(addressLocator).sendKeys(address);
        return this;
    }
    //Метод для заполнения станции метро
    public OrderPage sendMetroStation(String metroStation) {
        driver.findElement(metroStationLocator).click();
        driver.findElement(metroStationLocator).sendKeys(metroStation, Keys.ARROW_DOWN, Keys.ENTER);
        return this;
    }
    //Метод для заполнения номера телефона
    public OrderPage sendPhoneNumber (String phoneNumber) {
        driver.findElement(phoneNumberLocator).sendKeys(phoneNumber);
        return this;
    }
    public OrderPage clickNextButton () {
        driver.findElement(nextButton).click();
        return this;
    }
    //Метод для заполнения даты заказа
    public OrderPage sendDateOrder (String dateOrder) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(dateOrderLocator)).sendKeys(dateOrder);
       //driver.findElement(dateOrderLocator).sendKeys(dateOrder);
        return this;
    }

    //Метод для заполнения длительности аренды
    public OrderPage sendPeriodRent (/*String periodRent*/) {
       // driver.findElement(periodRentLocator).sendKeys(periodRent);
        driver.findElement(periodRentLocator).click();
        driver.findElement(sutki).click();
        return this;
    }

    public OrderPage clickOrderButton () {
        driver.findElement(orderButton).click();
        return this;
    }

    /*to_do: пока так, но надо подумать над обработкой исключений,
     *если не дождались оформления заказа, возможно есть смысл сюда прикрутить перехват
     *или изменить немного процедуру, чтобы в итоге падение было не по таймауту, а сразу что-то
     *типо NoSuchElementException*/
    public boolean clickConfirmationButton () {
        driver.findElement(confirmationButton).click();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderComleted)).isDisplayed();
    }


}