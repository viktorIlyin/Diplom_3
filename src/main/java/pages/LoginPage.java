package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LoginPage {

    private final String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";

    @FindBy(how = How.XPATH, using = "//div/input[@type='text']")
    private SelenideElement userEmailField;

    @FindBy(how = How.XPATH, using = "//h2[text()='Вход']")
    private SelenideElement loginPageTitle;

    @FindBy(how = How.XPATH, using = "//div/input[@type='password']")
    private SelenideElement userPasswordField;

    @FindBy(how = How.XPATH, using = "//form//button[text()='Войти']")
    private SelenideElement loginButton;

    @FindBy(how = How.XPATH, using = "//a[text()='Зарегистрироваться']")
    private SelenideElement registerButton;

    @FindBy(how = How.XPATH, using = "//a[text()='Восстановить пароль']")
    private SelenideElement recoveryPasswordButton;

    @Step("Вход в аккаунт по кнопке Войти")
    public void clickLoginButton() {
        loginButton.click();
    }

    @Step("Нажатие кнопки Зарегистрироваться")
    public void clickRegistrationButton() {
        registerButton.click();
    }

    @Step("Нажатие кнопки Восстановить пароль")
    public void clickRecoveryButton() {
        recoveryPasswordButton.click();
    }

    @Step("Проверка загрузки страницы Логина")
    public boolean isOpen() {
        loginPageTitle.shouldBe(visible);
        return url().equals(LOGIN_PAGE_URL);
    }

    @Step("Авторизация пользователя. Ввод данных и вход в личный кабинет по кнопке Войти")
    public void enterCredentialsAndClickEnter(String email, String password) {
        userEmailField.setValue(email);
        userPasswordField.setValue(password);
        clickLoginButton();
    }
}