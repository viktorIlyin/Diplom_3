import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RecoveryPasswordForm;
import pages.RegistrationPage;

import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserAuthorizationTest {

    final String LOGIN_URL = "https://stellarburgers.nomoreparties.site/login";
    MainPage mainPage = open(MainPage.BURGERS_MAIN_PAGE_URL, MainPage.class);
    LoginPage loginPage = page(LoginPage.class);
    RegistrationPage registrationPage = page(RegistrationPage.class);
    RecoveryPasswordForm recoveryPage = page(RecoveryPasswordForm.class);
    UserOperations userHelper;

    @Before
    public void setUp() {
        //Раскомментировать 32 строку для проверки в Яндекс браузере.
        //System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        userHelper = new UserOperations();
    }

    @Test
    @DisplayName("Вход по кнопке Войти в аккаунт")
    public void userLoginByEnterAccountButton() {
        Map<String, String> userData = userHelper.register();
        mainPage.clickEnterAccountButton();
        loginPage.enterCredentialsAndClickEnter(userData.get("email"), userData.get("password"));
        assertTrue(mainPage.isMainPageLoggedAuthorised());

    }

    @Test
    @DisplayName("Вход по кнопке Личный кабинет")
    public void userLoginByPersonalCabinetButton() {
        mainPage.clickPersonalCabinetButton();
        String actualUrl = url();
        assertEquals("Не прошел переход на страницу логина", LOGIN_URL, actualUrl);

    }

    @Test
    @DisplayName("Вход через кнопку на форме регистрации")
    public void userLoginFromRegistrationForm() {
        mainPage.clickEnterAccountButton();
        loginPage.clickRegistrationButton();
        registrationPage.clickEnterAccountButton();
        String actualUrl = url();
        assertEquals("Не прошел переход на страницу логина", LOGIN_URL, actualUrl);

    }

    @Test
    @DisplayName("Вход через кнопку на форме восстановления пароля")
    public void userLoginFromPasswordRecoveryForm() {
        mainPage.clickEnterAccountButton();
        loginPage.clickRecoveryButton();
        recoveryPage.clickEnterButton();
        String actualUrl = url();
        assertEquals("Не прошел переход на страницу логина", LOGIN_URL, actualUrl);
    }

    @After
    public void terminate() {
        userHelper.delete();
        getWebDriver().quit();
    }
}