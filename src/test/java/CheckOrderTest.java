import io.github.bonigarcia.wdm.WebDriverManager;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;
import pages.OrderPage;

import static constants.Constants.ERROR_ORDER_MESSAGE;
import static constants.Constants.ORDER_PLACED;
import static org.hamcrest.CoreMatchers.containsString;


//Параметризированный тест
@RunWith(Parameterized.class)
public class CheckOrderTest {

    private WebDriver driver;

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    private final int x;
    private final String firstName;
    private final String secondName;
    private final String adress;
    private final String metroStation;
    private final String phoneNumber;
    private final String date;
    private final String color;
    private final String comment;


    public CheckOrderTest(int x, String firstName, String secondName, String adress, String metroStation,
                          String phoneNumber, String date, String color, String comment) {
        this.x = x;
        this.firstName = firstName;
        this.secondName = secondName;
        this.adress = adress;

        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.color = color;
        this.comment = comment;
    }


    @Parameterized.Parameters
    public static Object[][] getCredentials() {

        return new Object[][]{
                {1, "Сергей", "Иванов", "Казань", "Сокольники", "897667664746", "13.05.2024", "grey", "буду вас ждать"},
                {2, "Петр", "Михайлович", "Казань", "Черкизовская", "897667664746", "13.05.2024", "grey", "буду вас ждать"},
        };
    }

    @Test
    public void positiveCheckOrderSamokat() {
        MainPage mainPage = new MainPage(driver);
        mainPage.setUp();
        mainPage.cookieExcept();
        // Клик по кнопке "Заказать"
        mainPage.clickOrder(x);
        // Создали объект страницы "Заказать"
        OrderPage orderPage = new OrderPage(driver);
        // Ввод имени
        orderPage.enterName(firstName);
        // Ввод фамилии
        orderPage.enterSurname(secondName);
        // Ввод адреса
        orderPage.enterAdress(adress);
        // Выбор метро - клик по полю
        orderPage.clickMetro();
        // Выбор станции
        orderPage.choseMetroStation(metroStation);
        // Ввод телефона в Поле ввода номера телефона
        orderPage.enterPhoneNumber(phoneNumber);
        // Нажать кнопку "Далее"
        orderPage.clickNextButton();
        // Перешли на следующий экран "Про аренду"
        // Кликнуть в поле выбора даты и поставить нужную и нажать Enter
        orderPage.enterDate(date);
        // Клик по выбору срока аренды и Выбор срока аренды в выпадающем меню - первый из списка
        orderPage.clickTimeRent();
        // Выбор цвета самоката (первый чек-бокс)
        orderPage.clickColorSamokat(color);
        // Ввод комментария в Поле "Комментарий для курьера"
        orderPage.enterСomment(comment);
        // Нажать кнопку Заказать
        orderPage.clickOrder();
        //Проверка, что окно подтверждения заказа появилось
        orderPage.chekOrderVerificationWindow();
        // Клик на кнопку "Да"
        orderPage.clickYesOrder();
        //Прверка текста в финальном окне подтверждения заказа
        MatcherAssert.assertThat(ERROR_ORDER_MESSAGE,
                orderPage.getFinalOrderMessage(), containsString(ORDER_PLACED));
    }

    @After
    public void tearDown() {
        // Закрой браузер
        driver.quit();
    }

}