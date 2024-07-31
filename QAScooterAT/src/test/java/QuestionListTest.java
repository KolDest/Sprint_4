import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.HomePage;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class QuestionListTest {

    private static WebDriver driver;
    private static HomePage homePage;
    private static List<WebElement> faqList;

    //Поля для параметризации теста
    private final int qaNum;
    private final String question;
    private final String answer;

    public QuestionListTest(int qaNum, String question, String answer) {
        this.qaNum = qaNum;
        this.question = question;
        this.answer = answer;
    }

    @Parameterized.Parameters
    public static Object[][] getItems() {
        return new Object[][] {
                {0, "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @BeforeClass
    public static void turningOn() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        homePage = new HomePage(driver);
        faqList = homePage.getListOfQuestions();
        homePage.clickCookieConfirmButton();
    }

    @Test
    public void checkQuestionListTest() {
        assertTrue(homePage.checkOneFromListOfQuestions(faqList.get(qaNum), question, answer));
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
