package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {
    //WebDriver
    private WebDriver driver;
    //Локатор вопроса из блока "Вопросы о важном"
    private By questionItem = By.className("accordion__item");
    //Локатор кнопки выпадения ответа из блока "Вопросы о важном"
    private By buttonToQuestionItem = By.className("accordion__button");
    //Локатор текста ответа
    private By answerText = By.cssSelector("div>p");
    //Локатор кнопки согласия кукис
    private By cookieConfirmButton = By.id("rcc-confirm-button");
    //Локатор кнопки заказать в верхней части экрана
    private By upOrderButton = By.cssSelector(".Header_Nav__AGCXC > .Button_Button__ra12g");
    //Локатор кнопки заказать в нижней части экрана
    private By downOrderButton = By.cssSelector(".Home_FinishButton__1_cWm > .Button_Button__ra12g");
    //Локатор кнопки статус заказа
    private By orderStatusButton = By.cssSelector(".Header_Nav__AGCXC > .Header_Link__1TAG7");



   public HomePage(WebDriver driver) {
       this.driver = driver;
   }
   //Нажать кнопку согласия кукис
    public void clickCookieConfirmButton() {
        driver.findElement(cookieConfirmButton).click();
    }
    //Получить список элементов из блока "Вопросы о важном"
    public List<WebElement> getListOfQuestions() {
        return driver.findElements(questionItem);
    }
    //Проверить один блок вопрос/ответа на соотвествие текста
    public boolean checkOneFromListOfQuestions(WebElement element, String question, String answer) {
        element.findElement(buttonToQuestionItem).click();
        return question.equals(element.findElement(buttonToQuestionItem).getText())
                && answer.equals(element.findElement(answerText).getText());
    }
    //Нажать кнопку Заказать в верхней части экрана
    public void clickUpOrderButton() {
        driver.findElement(upOrderButton).click();
    }
    //Нажать кнопку Заказать в нижней части экрана
    public void clickDownOrderButton() {
        driver.findElement(downOrderButton).click();
    }
    //Нажать кнопку Cтатус заказа
    public void clickOrderStatusButton() {
        driver.findElement(orderStatusButton).click();
    }

}
