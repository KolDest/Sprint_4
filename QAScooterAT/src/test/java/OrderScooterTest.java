import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobjects.HomePage;
import pageobjects.OrderPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderScooterTest {
    WebDriver driver;
    HomePage homePage;
    OrderPage orderPage;

    private final String name;
    private final String family;
    private final String address;
    private final int metroStation;
    private final String phone;
    private final String date;
    private final int rentalPeriod;
    private final String colour;
    private final String comment;

    public OrderScooterTest(String name, String family, String address, int metroStation, String phone,
                            String date, int rentalPeriod, String colour, String comment) {
        this.name = name;
        this.family = family;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getItems() {
        return new Object[][] {
                {"Петя", "Иванов", "Москва", 3, "89601121454", "04.08.2024", 2, "black", ""},
                {"Саня", "Костров", "Воронеж", 4, "89621141454", "05.08.2024", 1, "grey", ""},
                {"Ваня", "Петров", "Вологда", 5, "89621141454", "06.08.2024", 3, "black and grey", "Спущусь вниз"},
                {"Влад", "Стулов", "Вологда", 2, "89621141454", "07.08.2024", 4, "", "Буду один"},
        };
    }

    @Before
    public void turningOn() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        homePage = new HomePage(driver);
        orderPage = new OrderPage(driver);
        homePage.clickCookieConfirmButton();
    }

    @Test
    public void upOrderScooterTest() {
        homePage.clickUpOrderButton();
        orderPage.doOrder(name, family, address, metroStation, phone,
                date, rentalPeriod, colour, comment);
        assertTrue(orderPage.checkOrderDone());
    }

    @Test
    public void downOrderScooterTest() {
        homePage.clickDownOrderButton();
        orderPage.doOrder(name, family, address, metroStation, phone,
                date, rentalPeriod, colour, comment);
        assertTrue(orderPage.checkOrderDone());
    }

    @After
    public void tearDown() {
        // Закрой браузер
        driver.quit();
    }
}
