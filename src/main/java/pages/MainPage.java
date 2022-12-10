package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.WebDriverRunner.url;

public class MainPage {

    public final static String BURGERS_MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";


    @FindBy(how = How.XPATH, using = "//p[text()='Личный Кабинет']")
    private SelenideElement personalCabinetButton;

    @FindBy(how = How.XPATH, using = "//button[text()='Войти в аккаунт']")
    private SelenideElement enterAccountButton;

    @FindBy(how = How.XPATH, using = "//span[text()='Булки']")
    private SelenideElement bunsButton;
    @FindBy(how = How.XPATH, using = "//h2[text()='Булки']")
    private SelenideElement bunsSectionLabel;

    @FindBy(how = How.XPATH, using = "//span[text()='Соусы']")
    private SelenideElement saucesButton;
    @FindBy(how = How.XPATH, using = "//h2[text()='Соусы']")
    private SelenideElement saucesSectionLabel;

    @FindBy(how = How.XPATH, using = "//span[text()='Начинки']")
    private SelenideElement fillingsButton;
    @FindBy(how = How.XPATH, using = "//h2[text()='Начинки']")
    private SelenideElement fillingsSectionLabel;

    @FindBy(how = How.XPATH, using = "//button[text()='Оформить заказ']")
    private SelenideElement makeOrderButton;


    @Step("Метод для входа в аккаунт по кнопке Войти в аккаунт")
    public void clickEnterAccountButton() {
        enterAccountButton.click();
    }

    @Step("Метод для входа в аккаунт по кнопке Личный кабинет")
    public void clickPersonalCabinetButton() {
        personalCabinetButton.click();
    }

    @Step("Проверка отображения вкладки Булки по умолчанию")
    public boolean isBunsTabOpen() {
        bunsSectionLabel.shouldBe(visible);
        return true;
    }

    @Step("Проверка переключения на вкладку Соусы")
    public void goToSaucesTab() {
        saucesButton.click();
    }

    @Step("Проверка видимости вкладки Соусы")
    public boolean isSaucesTabVisible() {
        saucesSectionLabel.shouldBe(visible);
        return true;
    }

    @Step("Проверка переключения на вкладку Начинки")
    public void goToFillingsTab() {
        fillingsButton.click();
    }

    @Step("Проверка видимости вкладки Начинки")
    public boolean isFillingsTabVisible() {
        fillingsSectionLabel.shouldBe(visible);
        return true;
    }

    @Step("Проверка переключения на вкладку Булки")
    public void goToBunsTab() {
        bunsButton.click();
        isBunsTabOpen();
    }

    @Step("Проверка видимости вкладки Начинки")
    public boolean isBunsTabVisible() {
        bunsSectionLabel.shouldBe(visible);
        return true;
    }

    @Step("Проверка отображения кнопки Оформить заказ")
    public boolean isMainPageLoggedAuthorised() {
        makeOrderButton.shouldBe(visible, enabled);
        return url().equals(BURGERS_MAIN_PAGE_URL);
    }
}