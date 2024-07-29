package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {
    //WebDriver
    private WebDriver driver;

    //Локатор элемента ввода имени
    private By nameInput = By.cssSelector("[placeholder='* Имя']");
    //Локатор элемента ввода фамилии
    private By familyInput = By.cssSelector("[placeholder='* Фамилия']");
    //Локатор элемента ввода адреса
    private By addressInput = By.cssSelector(" [placeholder='* Адрес: куда привезти заказ']");
    //Локатор элемента выбора станции метро
    private By metroStationSelection = By.cssSelector("[placeholder='* Станция метро']");
    //Локатор элемента ввода телефона
    private By phoneInput = By.cssSelector("[placeholder='* Телефон: на него позвонит курьер']");
    //Локатор элемента ввода даты
    private By dateInput = By.cssSelector("[placeholder='* Когда привезти самокат']");
    //Локатор элемента выбора срока аренды
    private By rentalPeriodSelection = By.className("Dropdown-arrow");
    //Локатор элемента выбора черного цвета самоката
    private By blackColorInput = By.id("black");
    //Локатор элемента выбора серого цвета самоката
    private By greyColorInput = By.id("grey");
    //Локатор элемента ввода комментария для курьера
    private By commentInput = By.cssSelector("[placeholder='Комментарий для курьера']");
    //Локатор элемента текст об успешном заказе
    private By orderDoneText = By.xpath(".//div [text()='Заказ оформлен']");


    //Локатор кнопки Далее
    private By nextButton = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");
    //Локатор кнопки Заказать
    private By orderButton = By.xpath(".//button[text()='Заказать' and @class='Button_Button__ra12g Button_Middle__1CSJM']");
    //Локатор кнопки Да
    private By yesButton = By.xpath(".//button[text()='Да' and @class='Button_Button__ra12g Button_Middle__1CSJM']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //Выбор станции метро из выпадающего списка
    public void chooseMetroStation(int stationNumber) {
        driver.findElement(metroStationSelection).click();
        driver.findElement(By.cssSelector("[value='"+ stationNumber + "']")).click();
    }
    //Выбор срока аренды из выпадающего списка
    public void chooseRentalPeriod(int rentalPeriod) {
        driver.findElement(rentalPeriodSelection).click();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Dropdown-menu")));
        driver.findElement(By.xpath(".//div [@class='Dropdown-option']["+ rentalPeriod + "]")).click();
    }
    //Выбор цвета самоката
    public void chooseColour(String colour) {
        switch (colour) {
            case "black":
                driver.findElement(blackColorInput).click();
                break;
            case "grey":
                driver.findElement(greyColorInput).click();
                break;
            case "black and grey":
                driver.findElement(blackColorInput).click();
                driver.findElement(greyColorInput).click();
                break;
        }
    }
    //Ввод данных на странице "Для кого самокат"
    public void inputAllDataForOrderAboutCustomer(String name, String family, String address, int metroStation, String phone){
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(familyInput).sendKeys(family);
        driver.findElement(addressInput).sendKeys(address);
        chooseMetroStation(metroStation);
        driver.findElement(phoneInput).sendKeys(phone);
    }
    //Ввод данных на странице "Про аренду"
    public void inputAllDataForOrderAboutScooter(String date, int rentalPeriod, String colour,  String comment){
        driver.findElement(dateInput).sendKeys(date);
        chooseRentalPeriod(rentalPeriod);
        chooseColour(colour);
        driver.findElement(commentInput).sendKeys(comment);
    }
    //Сделать Заказ
    public void doOrder(String name, String family, String address, int metroStation, String phone,
                        String date, int rentalPeriod, String colour,  String comment) {
        inputAllDataForOrderAboutCustomer(name, family, address, metroStation, phone);
        clickNextButton();
        inputAllDataForOrderAboutScooter(date, rentalPeriod, colour, comment);
        clickOrderButton();
        clickYesButton();
    }
    //Проверка появления уведомления об успешном заказе
    public boolean checkOrderDone() {
        return driver.findElement(orderDoneText).isDisplayed();
    }
    //Нажать кнопку Далее
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }
    //Нажать кнопку Заказать
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }
    //Нажать кнопку Да
    public void clickYesButton() {
        driver.findElement(yesButton).click();
    }
}
