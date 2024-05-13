package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static constants.Constants.BASE_URL;

public class MainPage {

    private final WebDriver driver;
    // Кнопка заказать из шапки
    private final String orderButtonInHeader = ".//button[@class='Button_Button__ra12g']";
    //Кнопка заказть из середины
    private final String orderButtonMiddle = ".//div[@class='Home_FinishButton__1_cWm']/button";

    public MainPage(WebDriver driver) {this.driver = driver;}

    // Открыть страницу с сайтом
    public void setUp() {
        driver.get(BASE_URL);
    }

    public void cookieExcept() {
        WebElement cookie = driver.findElement(By.id("rcc-confirm-button"));
        cookie.click();
    }

    // Кликнуть по строке в "Вопросы о важном"
    public void clickStringInFaq(String accordeonList) {
        WebElement element = driver.findElement(By.id(accordeonList));
        // Скролл до элемента
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        // Явное ожидание видимости элемента
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(By.id(accordeonList)));
        // Кликаем по строке
        element.click();
    }

    // Получить текст из строки "Вопросы о важном"
    public String getTextFromQuestion(String accordeonList) {
        WebElement element = driver.findElement(By.id(String.valueOf(accordeonList)));
        String actualTextAccordeonHeader = element.getText();
        return actualTextAccordeonHeader;
    }

    // Получить текст из выпавшей панели (после клика по строке в "Вопросы о важном)
    public String getTextFromString(String accordeonList) {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(accordeonList)));
        String actualTextAccordeonPanel = driver.findElement(By.id(accordeonList)).getText();
        return actualTextAccordeonPanel;
    }

    // Клик по кнопке "Заказать"
    public void clickOrder(int n) {

        if (n==1) {
            driver.findElement(By.xpath(orderButtonInHeader)).click();
        } else {
            // Скролл до элемента
            WebElement element = driver.findElement(By.xpath(orderButtonMiddle));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            driver.findElement(By.xpath(orderButtonMiddle)).click();
        }
    }
}