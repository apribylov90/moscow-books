package ru.alex.bookstore.tests;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import ru.alex.bookstore.config.AuthConfig;
import ru.alex.bookstore.config.GeneralConfig;
import ru.alex.bookstore.config.InitConfig;
import ru.alex.bookstore.pages.LoginPage;
import ru.alex.bookstore.pages.MainPage;
import ru.alex.bookstore.pages.elements.HeaderElement;
import ru.alex.bookstore.pages.elements.MessagePopupElement;

import static ru.alex.bookstore.utils.AllureAttach.*;

public class BaseTest {
    protected WebDriver driver;
    private  final InitConfig config = new InitConfig();
    protected final GeneralConfig generalConfig = ConfigFactory.create(GeneralConfig.class, System.getProperties());
    protected final AuthConfig authConfig = ConfigFactory.create(AuthConfig.class, System.getProperties());

    protected MainPage mainPage;
    protected HeaderElement header;
    protected MessagePopupElement popup;

    @BeforeEach
    public void setUp() {

        driver = config.getWebDriver();
        mainPage = new MainPage(driver);
        header = new HeaderElement(driver);
        popup = new MessagePopupElement(driver);

        System.out.println("Setting up test in Thread: " + Thread.currentThread().getName());
        System.out.println("Available processors: " + Runtime.getRuntime().availableProcessors());

    }

    @AfterEach
    public void after() {
        attachPageSource(driver);
        attachScreenshot(driver);
        attachVideo(driver);

//        try {
//            Thread.sleep(15000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        config.quitWebDriver();
    }

}
