package pages;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static constants.Constants.ERROR_ORDER_ALERT;
import static constants.Constants.WANT_TO_ORDER;
import static org.hamcrest.CoreMatchers.containsString;

public class OrderPage {
    private final WebDriver driver;
    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Ввод имени в Поле ввода имени
    public OrderPage enterName(String firstName) {
        driver.findElement(By.xpath("//input[@placeholder= '* Имя']")).sendKeys(firstName);
        return this;
    }

    // Ввод фамилии в Поле ввода фамилии
    public OrderPage enterSurname(String secondName) {
        driver.findElement(By.xpath("//input[@placeholder= '* Фамилия']")).sendKeys(secondName);
        return this;
    }

    // Ввод адреса в Поле ввода адреса
    public OrderPage enterAdress(String adress) {
        driver.findElement(By.xpath("//input[@placeholder= '* Адрес: куда привезти заказ']")).sendKeys(adress);
        return this;
    }

    // Выбор метро - клик по полю
    public void clickMetro() {
        driver.findElement(By.className("select-search")).click();
    }

    // Ввод названия станции и Клик по 1 выпавшей станции
    public void choseMetroStation(String metroStation) {
        driver.findElement(By.className("select-search__input")).sendKeys(metroStation);
        driver.findElement(By.className("select-search__select")).click();
    }

    // Ввод телефона в Поле ввода номера телефона
    public OrderPage enterPhoneNumber(String phoneNumber) {
        driver.findElement(By.xpath("//input[@placeholder= '* Телефон: на него позвонит курьер']")).sendKeys(phoneNumber);
        return this;
    }

    // Нажать кнопку "далее"
    public void clickNextButton() {
        driver.findElement(By.xpath("//button[@class= 'Button_Button__ra12g Button_Middle__1CSJM']")).click();
    }

    // Выбрать дату
    public OrderPage enterDate(String date) {
        driver.findElement(By.xpath("//input[@placeholder= '* Когда привезти самокат']")).sendKeys(date);
        driver.findElement(By.xpath("//input[@placeholder= '* Когда привезти самокат']")).sendKeys(Keys.ENTER);
        return this;
    }

    // Выбор срока аренды
    public void clickTimeRent() {
        driver.findElement(By.className("Dropdown-placeholder")).click();
        driver.findElement(By.className("Dropdown-option")).click();
    }

    // Выбор цвета самоката
    public void clickColorSamokat(String color) {
        driver.findElement(By.id(color)).click();
    }

    // Ввод комментария
    public OrderPage enterСomment(String comment) {
        driver.findElement(By.xpath("//input[@placeholder='Комментарий для курьера']")).sendKeys(comment);
        return this;
    }
    // Нажать кнопку Заказать
    public void clickOrder() {
        driver.findElement(By.xpath("//button[(@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать')]")).click();
    }
    //Проверка, что окно подтверждения заказа появилось
    public void chekOrderVerificationWindow() {
        MatcherAssert.assertThat(ERROR_ORDER_ALERT,
                driver.findElement(By.className("Order_ModalHeader__3FDaJ")).getText(),
                containsString(WANT_TO_ORDER));
    }
    // Клик на кнопку "Да"
    public void clickYesOrder() {
        //WebElement element = driver.findElement(By.xpath("//button[(@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да')]"));
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Order_Buttons__1xGrp")));
        driver.findElement(By.xpath("//button[(@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да')]")).click();

    }

    public OrderPage che(String comment) {
        driver.findElement(By.xpath("//input[@placeholder= 'Комментарий для курьера']")).sendKeys(comment);
        return this;
    }

    // Зщдучаем текст в окне подтверждения заказа
    public String getFinalOrderMessage(){
        return driver.findElement(By.className("Order_ModalHeader__3FDaJ")).getText();
    }

}
