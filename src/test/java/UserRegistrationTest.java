import Pages.LoginPage;
import Pages.MainPage;
import Pages.RegistrationPage;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.Assert.assertTrue;

public class UserRegistrationTest {


    MainPage mainPage = open(MainPage.BURGERS_MAIN_PAGE_URL, MainPage.class);
    LoginPage loginPage = page(LoginPage.class);
    RegistrationPage registrationPage = page(RegistrationPage.class);

    @Before
    public void setUp() {
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }

    @Test
    @DisplayName("Регистрация с валидными данными пользователя")
    public void userRegistrationIsPossible() {

        mainPage.clickEnterAccountButton();
        loginPage.clickRegistrationButton();
        String userName = RandomStringUtils.randomAlphabetic(7);
        String userEmail =
                RandomStringUtils.randomAlphabetic(4) + "@" + RandomStringUtils.randomAlphabetic(4) + ".ru";
        String userPassword = RandomStringUtils.randomAlphabetic(7);

        registrationPage.setUserDataAndRegister(userName, userEmail, userPassword);
        loginPage.isOpen();
        loginPage.enterCredentialsAndClickEnter(userEmail, userPassword);
        assertTrue(mainPage.isMainPageLoggedAuthorised());
    }

    @Test
    @DisplayName("Регистрация пользователя с паролем короче 6 символов")
    public void userRegistrationWithIncorrectPassword() {

        mainPage.clickEnterAccountButton();
        loginPage.clickRegistrationButton();
        String userName = RandomStringUtils.randomAlphabetic(7);
        String userEmail = RandomStringUtils.randomAlphabetic(4) + "@" + RandomStringUtils.randomAlphabetic(4) + ".ru";
        String userIncorrectPassword = RandomStringUtils.randomAlphabetic(5);
        registrationPage.setUserDataAndRegister(userName, userEmail, userIncorrectPassword);
        registrationPage.checkErrorTextPopUp();
    }

    @After
    public void terminate() {
        getWebDriver().quit();
    }
}