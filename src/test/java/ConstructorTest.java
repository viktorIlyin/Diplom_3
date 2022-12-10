import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.Assert.assertTrue;

public class ConstructorTest {
    MainPage mainPage = open(MainPage.BURGERS_MAIN_PAGE_URL, MainPage.class);

    @Before
    public void setUp() {
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }

    @Test
    @DisplayName("Проверка открытия конструктора. Вкладка Булки открыта по умолчанию")
    public void checkIsBunsTabOpen() {
        mainPage.isBunsTabOpen();
    }

    @Test
    @DisplayName("Проверка работы вкладки Соусы")
    public void isSaucesTabWorks() {
        mainPage.goToSaucesTab();
        assertTrue("Вкладка Соусы не открыта", mainPage.isSaucesTabVisible());
    }

    @Test
    @DisplayName("Проверка работы вкладки Начинки")
    public void isFillingsTabWorks() {
        mainPage.goToFillingsTab();
        assertTrue("Вкладка Начинки не открыта", mainPage.isFillingsTabVisible());
    }

    @Test
    @DisplayName("Проверка работы вкладки Булки")
    public void isBunsTabWorks() {
        mainPage.goToFillingsTab();
        mainPage.goToBunsTab();
        assertTrue("Вкладка Начинки не открыта", mainPage.isBunsTabVisible());
    }

    @After
    public void terminate() {
        getWebDriver().quit();
    }
}
